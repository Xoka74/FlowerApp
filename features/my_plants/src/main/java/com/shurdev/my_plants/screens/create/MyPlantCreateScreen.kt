package com.shurdev.my_plants.screens.create

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.forms.FormEditingState
import com.shurdev.domain.forms.FormState
import com.shurdev.domain.forms.FormSubmittedState
import com.shurdev.domain.models.myPlant.PlantWatering
import com.shurdev.domain.models.plant.Illumination
import com.shurdev.domain.models.plant.ToxicCategory
import com.shurdev.domain.models.plant.WateringFrequency
import com.shurdev.my_plants.components.MyPlantFormWidget
import com.shurdev.my_plants.forms.MyPlantForm
import com.shurdev.my_plants.screens.create.viewModel.MyPlantCreateViewModel
import com.shurdev.ui_kit.layouts.ConfirmLeaveScreenLayout
import java.time.LocalDateTime

@Composable
internal fun MyPlantCreateRoute(
    onBackInvoked: () -> Unit,
) {
    val viewModel = hiltViewModel<MyPlantCreateViewModel>()

    val form by viewModel.formDataState.collectAsState()
    val formState by viewModel.uiState.collectAsState()

    MyPlantCreateScreen(
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
internal fun MyPlantCreateScreen(
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

@Preview
@Composable
internal fun MyPlantCreateScreenPreview() {
    var frequency by remember {
        mutableStateOf(WateringFrequency.OnceAWeek)
    }

    Box(Modifier) {
        MyPlantCreateScreen(
            onNameChanged = {},
            onCreatePlantClick = {},
            onFormSubmit = {},
            formState = FormEditingState,
            hasChangesCheck = { false },
            onOtherInfoSelection = {},
            onWateringSelectionChanged = {},
            onLastWateringTimeChanged = {},
            onImageLoaded = {},
            onWateringFrequencyChanged = {
                frequency = it
            },
            onIlluminationChanged = {},
            onToxicCategorySelected = {},
            onToxicCategoryUnselected = {},
            form = MyPlantForm(
                name = "",
                watering = PlantWatering(
                    lastWateringTime = LocalDateTime.now(),
                    frequency = frequency
                )
            ),
        )
    }
}