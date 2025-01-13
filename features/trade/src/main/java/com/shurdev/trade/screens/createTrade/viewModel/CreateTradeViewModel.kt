package com.shurdev.trade.screens.createTrade.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.plant.Plant
import com.shurdev.domain.models.trade.Trade
import com.shurdev.domain.repositories.TradeRepository
import com.shurdev.trade.screens.createTrade.models.CreateTradeForm
import com.shurdev.trade.screens.createTrade.models.CreateTradeFormValidationError
import com.shurdev.ui_kit.viewModel.trackChanges.TrackChangesFormViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTradeViewModel @Inject constructor(
    private val tradeRepository: TradeRepository
) : TrackChangesFormViewModel<CreateTradeFormValidationError, CreateTradeForm>(
    initialData = CreateTradeForm()
) {

    init{
        updateFormData {
            it.copy(
                plantToGive = Plant(
                    id = 1,
                    name = "Роза",
                    description = "Потрясающая роза, " +
                            "ежедневно радующая глаз " +
                            "своей красотой",
                    imageLink = "https://postila.ru/data/35/c9/c4/31/35c9c431d95e258318a20252c603ddc5feb288d5f9b31ac3a2ea23e4a2744eb4.jpg"
                )
            )
        }
    }

    override fun sendForm() {

        val plantToGet = formData.plantToGet
        val plantToGive = formData.plantToGive

        if (plantToGet == null || plantToGive == null) {
            return
        }

        viewModelScope.launch {

            runSuspendCatching {
                tradeRepository.createTrade(
                    trade = Trade(
                        plantToGet = plantToGet,
                        plantToGive = plantToGive,
                        authorName = formData.authorName,
                    )
                )
            }.onFailure {
                println("Error: $it")
                // TODO
            }
        }
    }

    fun updateCity(newCity: String) {
        updateFormData {
            it.copy(city = newCity)
        }
    }

    fun updateAuthorName(newAuthorName: String) {
        updateFormData {
            it.copy(authorName = newAuthorName)
        }
    }

    fun updatePlantToGive(newPlantToGive: Plant) {
        updateFormData {
            it.copy(
                plantToGive = newPlantToGive
            )
        }
    }

    fun updatePlantToGet(newPlantToGet: Plant) {
        updateFormData {
            it.copy(
                plantToGet = newPlantToGet
            )
        }
    }
}