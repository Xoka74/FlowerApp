package com.shurdev.my_plants.screens.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.forms.FormState
import com.shurdev.domain.forms.FormSubmittedState
import com.shurdev.domain.models.plant.Illumination
import com.shurdev.domain.models.plant.ToxicCategory
import com.shurdev.domain.models.plant.WateringFrequency
import com.shurdev.my_plants.components.MyPlantFormWidget
import com.shurdev.my_plants.forms.MyPlantForm
import com.shurdev.my_plants.screens.edit.viewModel.EditMyPlantViewModel
import com.shurdev.ui_kit.layouts.ConfirmLeaveScreenLayout
import java.time.LocalDateTime

@Composable
fun EditMyPlantRoute(
    onBackInvoked: () -> Unit,
    viewModel: EditMyPlantViewModel = hiltViewModel<EditMyPlantViewModel>(),
) {
    val form by viewModel.formDataState.collectAsState()
    val formState by viewModel.uiState.collectAsState()

    EditMyPlantScreen(
        form = form,
        formState = formState,
        onNameChanged = viewModel::updateName,
        onImageLoaded = viewModel::updateImage,
        onWateringSelectionChanged = viewModel::updateWateringSelection,
        onWateringFrequencyChanged = viewModel::updateWateringFrequency,
        onLastWateringTimeChanged = viewModel::updateLastWateringTime,
        onOtherInfoSelection = viewModel::updateOtherInfoSelection,
        onIlluminationChanged = viewModel::updateIllumination,
        onToxicCategorySelected = viewModel::addToxicCategory,
        onToxicCategoryUnselected = viewModel::removeToxicCategory,
        onCreatePlantClick = viewModel::submitForm,
        hasChangesCheck = viewModel::hasChanges,
        onFormSubmit = onBackInvoked,
    )
}

@Composable
fun EditMyPlantScreen(
    form: MyPlantForm,
    formState: FormState,
    onNameChanged: (String) -> Unit,
    onImageLoaded: (ByteArray?) -> Unit,
    onWateringSelectionChanged: (Boolean) -> Unit,
    onWateringFrequencyChanged: (WateringFrequency) -> Unit,
    onLastWateringTimeChanged: (LocalDateTime) -> Unit,
    onOtherInfoSelection: (Boolean) -> Unit,
    onIlluminationChanged: (Illumination) -> Unit,
    onToxicCategorySelected: (ToxicCategory) -> Unit,
    onToxicCategoryUnselected: (ToxicCategory) -> Unit,
    onCreatePlantClick: () -> Unit,
    hasChangesCheck: () -> Boolean,
    onFormSubmit: () -> Unit,
) {
    LaunchedEffect(formState) {
        if (formState is FormSubmittedState<*>) {
            onFormSubmit()
        }
    }

    ConfirmLeaveScreenLayout(
        onBackInvoked = onFormSubmit,
        showConfirmLeave = hasChangesCheck,
    ) {
        MyPlantFormWidget(
            form = form,
            formState = formState,
            onNameChanged = onNameChanged,
            onImageLoaded = onImageLoaded,
            onWateringSelectionChanged = onWateringSelectionChanged,
            onWateringFrequencyChanged = onWateringFrequencyChanged,
            onLastWateringTimeChanged = onLastWateringTimeChanged,
            onOtherInfoSelection = onOtherInfoSelection,
            onIlluminationChanged = onIlluminationChanged,
            onToxicCategorySelected = onToxicCategorySelected,
            onToxicCategoryUnselected = onToxicCategoryUnselected,
            onCreatePlantClick = onCreatePlantClick,
        )
    }
}