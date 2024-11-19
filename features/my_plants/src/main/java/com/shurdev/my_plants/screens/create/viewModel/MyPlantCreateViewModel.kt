package com.shurdev.my_plants.screens.create.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.CreateMyPlantIntent
import com.shurdev.domain.usecases.CreateMyPlantUseCase
import com.shurdev.my_plants.screens.create.models.CreatePlantForm
import com.shurdev.my_plants.screens.create.models.CreatePlantFormValidationError
import com.shurdev.ui_kit.viewModel.form.FormSubmissionErrorState
import com.shurdev.ui_kit.viewModel.form.FormSubmittedState
import com.shurdev.ui_kit.viewModel.form.FormSubmittingState
import com.shurdev.ui_kit.viewModel.trackChanges.TrackChangesFormViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPlantCreateViewModel @Inject constructor(
    private val createMyPlantUseCase: CreateMyPlantUseCase,
) : TrackChangesFormViewModel<CreatePlantFormValidationError, CreatePlantForm>(
    initialData = CreatePlantForm()
) {
    fun updateName(value: String) = updateFormData { it.copy(name = value) }

    override fun sendForm() {
        viewModelScope.launch {
            updateUiState { FormSubmittingState }

            val intent = CreateMyPlantIntent(name = formData.name)

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