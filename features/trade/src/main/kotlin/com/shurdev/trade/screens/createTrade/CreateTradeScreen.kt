package com.shurdev.trade.screens.createTrade

import StickyBottomColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.shurdev.domain.models.plant.Plant
import com.shurdev.trade.composables.PlantCard
import com.shurdev.trade.screens.createTrade.models.CreateTradeForm
import com.shurdev.trade.screens.createTrade.viewModel.CreateTradeViewModel
import com.shurdev.ui_kit.R
import com.shurdev.ui_kit.buttons.PrimaryButton
import com.shurdev.ui_kit.fields.AppTextField
import com.shurdev.ui_kit.layouts.ConfirmLeaveScreenLayout

@Composable
fun CreateTradeRoute(
    onBackInvoked: () -> Unit,
) {

    val viewModel = hiltViewModel<CreateTradeViewModel>()
    val form by viewModel.formDataState.collectAsState()
    val formState by viewModel.uiState.collectAsState()

    CreateTradeScreen(
        form = form,
        formState = formState,
        hasChangesCheck = { false },
        onBackInvoked = onBackInvoked,
        onCreateTradeClick = viewModel::submitForm,
        onCityChange = viewModel::updateCity,
        onAuthorNameChange = viewModel::updateAuthorName,
        onPlantToGetChange = viewModel::updatePlantToGet,
        onPlantToGiveChange = viewModel::updatePlantToGive
    )
}

@Composable
fun CreateTradeScreen(
    form: CreateTradeForm,
    formState: FormState,
    onCityChange: (String) -> Unit,
    onAuthorNameChange: (String) -> Unit,
    onPlantToGetChange: (Plant) -> Unit,
    onPlantToGiveChange: (Plant) -> Unit,
    hasChangesCheck: () -> Boolean,
    onBackInvoked: () -> Unit,
    onCreateTradeClick: () -> Unit,
) {

    val saveText = stringResource(R.string.save)

    ConfirmLeaveScreenLayout(
        onBackInvoked = onBackInvoked,
        showConfirmLeave = hasChangesCheck,
        title = stringResource(com.shurdev.trade.R.string.new_trade),
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

            PlantCard(
                plant = plantToGive,
                modifier = Modifier,
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

            val plantToGet = form.plantToGet

            PlantCard(
                plant = plantToGet,
                modifier = Modifier,
                onCardClick = {},
                placeholder = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        text = "Выберите желаемое растение",
                        textAlign = TextAlign.Center,
                    )
                }
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
                hint = "Город"
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
                hint = "Ваше имя"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateTradePreview() {
    CreateTradeScreen(
        form = CreateTradeForm(
            plantToGive = Plant(
                id = 1,
                name = "Роза",
                description = "Потрясающая роза, " +
                        "ежедневно радующая глаз " +
                        "своей красотой",
            )
        ),
        formState = FormEditingState,
        onBackInvoked = {},
        hasChangesCheck = { true },
        onCreateTradeClick = {},
        onCityChange = {},
        onPlantToGetChange = {},
        onPlantToGiveChange = {},
        onAuthorNameChange = {},
    )
}