package com.shurdev.trade.screens.createTrade

import StickyBottomColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.domain.forms.EditableState
import com.shurdev.domain.forms.FormEditingState
import com.shurdev.domain.forms.FormState
import com.shurdev.domain.forms.FormSubmittingState
import com.shurdev.domain.models.trade.PlantTrade
import com.shurdev.trade.composables.MyPlantCard
import com.shurdev.trade.models.MyPlantPresentation
import com.shurdev.trade.screens.createTrade.models.CreateTradeForm
import com.shurdev.trade.screens.createTrade.viewModel.CreateTradeViewModel
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.buttons.PrimaryButton
import com.shurdev.ui_kit.fields.AppTextField
import com.shurdev.ui_kit.layouts.ConfirmLeaveScreenLayout
import kotlinx.coroutines.launch

@Composable
fun CreateTradeRoute(
    onBackInvoked: () -> Unit,
    onPlantToGetClicked: () -> Unit,
    onPlantToGiveClicked: () -> Unit,
    getPlantToGet: () -> PlantTrade?,
    getPlantToGive: () -> PlantTrade?,
) {

    val viewModel = hiltViewModel<CreateTradeViewModel>()
    val form by viewModel.formDataState.collectAsState()
    val formState by viewModel.uiState.collectAsState()

    val plantToGive = getPlantToGive()

    plantToGive?.let {
        viewModel.updatePlantToGive(
            MyPlantPresentation(
                name = it.name,
                imageData = it.imageData
            )
        )
    }

    CreateTradeScreen(
        form = form,
        formState = formState,
        onCityChange = viewModel::updateCity,
        onAuthorNameChange = viewModel::updateAuthorName,
        onContactDataChange = viewModel::updateContactData,
        onPlantToGiveClicked = onPlantToGiveClicked,
        onPlantToGetNameChange = viewModel::updatePlantToGetName,
        hasChangesCheck = { false },
        onBackInvoked = onBackInvoked,
        onCreateTradeClick = viewModel::submitForm,
        onHandleError = viewModel::handleError
    )
}

@Composable
fun CreateTradeScreen(
    form: CreateTradeForm,
    formState: FormState,
    onCityChange: (String) -> Unit,
    onAuthorNameChange: (String) -> Unit,
    onContactDataChange: (String) -> Unit,
    onPlantToGiveClicked: () -> Unit,
    onPlantToGetNameChange: (String) -> Unit,
    hasChangesCheck: () -> Boolean,
    onBackInvoked: () -> Unit,
    onCreateTradeClick: () -> Unit,
    onHandleError: ((errorMessage: String, handler: (String) -> Unit) -> Unit)? = null,
) {

    val saveText = stringResource(R.string.save)

    val errorMessage = form.errorMessage

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    if (errorMessage != null) {
        LaunchedEffect(errorMessage) {
            onHandleError?.invoke(errorMessage) {
                scope.launch {
                    snackbarHostState.showSnackbar(errorMessage)
                }
            }
        }
    }

    Scaffold { padding ->
        Box {
            ConfirmLeaveScreenLayout(
                onBackInvoked = onBackInvoked,
                showConfirmLeave = hasChangesCheck,
                title = stringResource(com.shurdev.trade.R.string.new_trade)
            ) {
                StickyBottomColumn(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    stickyBottom = {
                        PrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = saveText,
                            onClick = onCreateTradeClick,
                            isLoading = formState is FormSubmittingState,
                            enabled = formState is EditableState
                        )
                    },
                ) {

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text(
                        text = "Отдать цветок:"
                    )

                    val plantToGive = form.plantToGive

                    MyPlantCard(
                        myPlant = plantToGive,
                        modifier = Modifier,
                        onCardClick = onPlantToGiveClicked,
                        placeholder = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 32.dp),
                                text = "Выберите отдаваемое растение",
                                textAlign = TextAlign.Center
                            )
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text(
                        text = "Желаемый цветок:"
                    )

                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = form.plantToGetName,
                        onTextChange = onPlantToGetNameChange,
                        hint = stringResource(com.shurdev.trade.R.string.desired_plant_name)
                    )

                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                    )

                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = form.city,
                        onTextChange = onCityChange,
                        hint = stringResource(com.shurdev.trade.R.string.city)
                    )

                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                    )

                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = form.authorName,
                        onTextChange = onAuthorNameChange,
                        hint = stringResource(com.shurdev.trade.R.string.your_name)
                    )

                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                    )

                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = form.contactData,
                        onTextChange = onContactDataChange,
                        hint = stringResource(com.shurdev.trade.R.string.contact_data)
                    )
                }
            }

            Box(
                modifier = Modifier.padding(padding),
                contentAlignment = Alignment.TopCenter
            ) {
                SnackbarHost(hostState = snackbarHostState)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateTradePreview() {
    CreateTradeScreen(
        form = CreateTradeForm(),
        formState = FormEditingState,
        onCityChange = {},
        onAuthorNameChange = {},
        onContactDataChange = {},
        onPlantToGiveClicked = {},
        hasChangesCheck = { true },
        onBackInvoked = {},
        onCreateTradeClick = {},
        onPlantToGetNameChange = {}
    )
}