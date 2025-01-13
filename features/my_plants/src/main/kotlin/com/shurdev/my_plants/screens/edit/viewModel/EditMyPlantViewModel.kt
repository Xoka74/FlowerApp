package com.shurdev.my_plants.screens.edit.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.forms.FormEditingState
import com.shurdev.domain.forms.FormPreparationFailedState
import com.shurdev.domain.forms.FormPreparationState
import com.shurdev.domain.forms.FormSubmissionErrorState
import com.shurdev.domain.forms.FormSubmittedState
import com.shurdev.domain.repositories.MyPlantsRepository
import com.shurdev.domain.usecases.EditMyPlantUseCase
import com.shurdev.my_plants.forms.MyPlantForm
import com.shurdev.my_plants.forms.MyPlantFormViewModel
import com.shurdev.my_plants.forms.toData
import com.shurdev.my_plants.forms.toForm
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditMyPlantViewModel @Inject constructor(
    private val myPlantsRepository: MyPlantsRepository,
    private val editMyPlantUseCase: EditMyPlantUseCase,
    savedStateHandle: SavedStateHandle,
) : MyPlantFormViewModel(
    initialData = MyPlantForm()
) {
    private val id = savedStateHandle["id"] ?: 0

    init {
        prepareData()
    }

    private fun prepareData() {
        updateUiState { FormPreparationState }

        viewModelScope.launch {
            runSuspendCatching {
                val plant = myPlantsRepository.getById(id)
                updateUiState { FormEditingState }
                updateFormData { plant.toForm() }
                saveChanges()
            }.onFailure {
                updateUiState { FormPreparationFailedState }
            }
        }
    }

    override fun sendForm() {
        viewModelScope.launch {
            runSuspendCatching {
                editMyPlantUseCase(id, formData.toData())
                updateUiState { FormSubmittedState(Unit) }
            }.onFailure {
                updateUiState { FormSubmissionErrorState }
            }
        }
    }
}