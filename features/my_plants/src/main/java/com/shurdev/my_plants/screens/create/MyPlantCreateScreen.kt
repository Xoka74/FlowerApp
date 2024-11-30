package com.shurdev.my_plants.screens.create

import StickyBottomColumn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.my_plants.screens.create.models.CreatePlantForm
import com.shurdev.my_plants.screens.create.models.CreatePlantFormValidationError
import com.shurdev.my_plants.screens.create.viewModel.MyPlantCreateViewModel
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.buttons.PrimaryButton
import com.shurdev.ui_kit.fields.AppTextField
import com.shurdev.ui_kit.layouts.ConfirmLeaveScreenLayout
import com.shurdev.ui_kit.viewModel.form.EditableState
import com.shurdev.ui_kit.viewModel.form.FormEditingState
import com.shurdev.ui_kit.viewModel.form.FormState
import com.shurdev.ui_kit.viewModel.form.FormSubmittedState
import com.shurdev.ui_kit.viewModel.form.FormSubmittingState
import com.shurdev.ui_kit.viewModel.form.FormValidationErrorState

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
        onCreatePlantClick = viewModel::submitForm,
        hasChangesCheck = viewModel::hasChanges,
        onBackInvoked = onBackInvoked,
    )
}

@Composable
internal fun MyPlantCreateScreen(
    form: CreatePlantForm,
    formState: FormState,
    onNameChanged: (String) -> Unit,
    onCreatePlantClick: () -> Unit,
    hasChangesCheck: () -> Boolean,
    onBackInvoked: () -> Unit,
) {
    // TODO: Cast it with less code
    val validationError =
        (formState as? FormValidationErrorState<*>)?.error as? CreatePlantFormValidationError

    LaunchedEffect(formState) {
        if (formState is FormSubmittedState<*>) {
            onBackInvoked()
        }
    }

    val startTypingHint = stringResource(R.string.start_typing)
    val saveText = stringResource(R.string.save)

    ConfirmLeaveScreenLayout(
        onBackInvoked = onBackInvoked,
        showConfirmLeave = hasChangesCheck,
    ) {
        StickyBottomColumn(
            modifier = Modifier.padding(20.dp),
            stickyBottom = {
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = saveText,
                    onClick = onCreatePlantClick,
                    isLoading = formState is FormSubmittingState,
                    enabled = formState is EditableState
                )
            },
        ) {
            val plantNameLabel = stringResource(R.string.plant_name)

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                text = form.name,
                hint = startTypingHint,
                label = plantNameLabel,
                error = validationError?.name,
                singleLine = true,
                onTextChange = onNameChanged,
            )
        }
    }
}

@Preview
@Composable
internal fun MyPlantCreateScreenPreview() {
    MyPlantCreateScreen(
        onNameChanged = {},
        onCreatePlantClick = {},
        onBackInvoked = {},
        formState = FormEditingState,
        hasChangesCheck = { false },
        form = CreatePlantForm(
            name = "",
        ),
    )
}