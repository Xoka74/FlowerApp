package com.shurdev.my_plants.screens.create.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.myPlant.MyPlantData
import com.shurdev.domain.usecases.CreateMyPlantUseCase
import com.shurdev.my_plants.forms.MyPlantFormViewModel
import com.shurdev.my_plants.forms.MyPlantForm
import com.shurdev.domain.forms.FormSubmissionErrorState
import com.shurdev.domain.forms.FormSubmittedState
import com.shurdev.domain.forms.FormSubmittingState
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPlantCreateViewModel @Inject constructor(
    private val createMyPlantUseCase: CreateMyPlantUseCase,
) : MyPlantFormViewModel(
    initialData = MyPlantForm()
) {
    override fun sendForm() {
        viewModelScope.launch {
            updateUiState { FormSubmittingState }

            val intent = MyPlantData(
                name = formData.name,
                plantWatering = formData.watering,
                otherInfo = formData.otherInfo,
                imageData = formData.imageData
            )

            runSuspendCatching {
                createMyPlantUseCase(intent)
            }.onSuccess {
                saveChanges()
                updateUiState { FormSubmittedState(Unit) }
            }.onFailure {
                updateUiState { FormSubmissionErrorState }
            }
        }
    }
}