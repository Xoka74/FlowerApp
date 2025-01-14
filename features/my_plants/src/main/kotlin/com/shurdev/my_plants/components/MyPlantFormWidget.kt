package com.shurdev.my_plants.components

import StickyBottomColumn
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shurdev.domain.forms.EditableState
import com.shurdev.domain.forms.FormState
import com.shurdev.domain.forms.FormSubmittingState
import com.shurdev.domain.forms.FormValidationErrorState
import com.shurdev.domain.models.plant.Illumination
import com.shurdev.domain.models.plant.ToxicCategory
import com.shurdev.domain.models.plant.WateringFrequency
import com.shurdev.my_plants.forms.MyPlantForm
import com.shurdev.my_plants.forms.MyPlantFormValidationError
import com.shurdev.my_plants.screens.create.composables.OtherInfoPicker
import com.shurdev.my_plants.screens.create.composables.PlantImagePicker
import com.shurdev.my_plants.screens.create.composables.WateringPicker
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.buttons.PrimaryButton
import com.shurdev.ui_kit.fields.AppTextField
import com.shurdev.ui_kit.utils.getByteArray
import java.time.LocalDateTime

@Composable
fun MyPlantFormWidget(
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
) {
    // TODO: Cast it with less code
    val validationError =
        (formState as? FormValidationErrorState<*>)?.error as? MyPlantFormValidationError

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
            model = form.imageData,
            placeholder = com.shurdev.my_plants.R.drawable.placeholder_image,
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