package com.shurdev.my_plants.screens.create

import StickyBottomColumn
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.models.myPlant.PlantWatering
import com.shurdev.domain.models.plant.Illumination
import com.shurdev.domain.models.plant.ToxicCategory
import com.shurdev.domain.models.plant.WateringFrequency
import com.shurdev.my_plants.screens.create.composables.OtherInfoPicker
import com.shurdev.my_plants.screens.create.composables.PlantImagePicker
import com.shurdev.my_plants.screens.create.composables.WateringPicker
import com.shurdev.my_plants.screens.create.models.CreatePlantForm
import com.shurdev.my_plants.screens.create.models.CreatePlantFormValidationError
import com.shurdev.my_plants.screens.create.viewModel.MyPlantCreateViewModel
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.buttons.PrimaryButton
import com.shurdev.ui_kit.fields.AppTextField
import com.shurdev.ui_kit.layouts.ConfirmLeaveScreenLayout
import com.shurdev.ui_kit.utils.getByteArray
import com.shurdev.ui_kit.viewModel.form.EditableState
import com.shurdev.ui_kit.viewModel.form.FormEditingState
import com.shurdev.ui_kit.viewModel.form.FormState
import com.shurdev.ui_kit.viewModel.form.FormSubmittedState
import com.shurdev.ui_kit.viewModel.form.FormSubmittingState
import com.shurdev.ui_kit.viewModel.form.FormValidationErrorState
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
    form: CreatePlantForm,
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
    // TODO: Cast it with less code
    val validationError =
        (formState as? FormValidationErrorState<*>)?.error as? CreatePlantFormValidationError

    LaunchedEffect(formState) {
        if (formState is FormSubmittedState<*>) {
            onFormSubmit()
        }
    }

    ConfirmLeaveScreenLayout(
        onBackInvoked = onFormSubmit,
        showConfirmLeave = hasChangesCheck,
    ) {
        StickyBottomColumn(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            stickyBottom = {
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.save),
                    onClick = onCreatePlantClick,
                    isLoading = formState is FormSubmittingState,
                    enabled = formState is EditableState
                )
            },
        ) {
            val context = LocalContext.current

            val photoPickerLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.PickVisualMedia(),
                onResult = { uri ->
                    val byteArray = uri?.getByteArray(context)

                    if (byteArray != null) {
                        onImageLoaded(byteArray)
                    }
                }
            )

            PlantImagePicker(
                model = form.imageData ?: com.shurdev.my_plants.R.drawable.flower_placeholder_1,
                onRemoveClick = { onImageLoaded(null) },
                onPickTap = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )

            Spacer(Modifier.height(16.dp))

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                text = form.name,
                hint = stringResource(R.string.start_typing),
                label = stringResource(R.string.plant_name),
                singleLine = true,
                onTextChange = onNameChanged,
                error = validationError?.nameError?.toErrorString(
                    required = stringResource(R.string.required_field),
                ),
            )

            Spacer(Modifier.height(16.dp))

            WateringPicker(
                watering = form.watering,
                onWateringSelectionChanged = onWateringSelectionChanged,
                onWateringFrequencyChanged = onWateringFrequencyChanged,
                onLastWateringTimeChanged = onLastWateringTimeChanged,
            )

            Spacer(Modifier.height(16.dp))

            OtherInfoPicker(
                otherInfo = form.otherInfo,
                onIlluminationChanged = onIlluminationChanged,
                onToxicCategorySelected = onToxicCategorySelected,
                onOtherInfoSelection = onOtherInfoSelection,
                onToxicCategoryUnselected = onToxicCategoryUnselected,
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
internal fun MyPlantCreateScreenPreview() {
    var frequency by remember {
        mutableStateOf(WateringFrequency.OnceAWeek)
    }

    Scaffold { padding ->
        Box(Modifier.padding(padding)) {
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
                form = CreatePlantForm(
                    name = "",
                    watering = PlantWatering(
                        lastWateringTime = LocalDateTime.now(),
                        frequency = frequency
                    )
                ),
            )
        }
    }
}