package com.shurdev.trade.screens.createTrade.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.trade.CreateTradeModel
import com.shurdev.domain.repositories.TradeRepository
import com.shurdev.trade.models.MyPlantPresentation
import com.shurdev.trade.screens.createTrade.models.CreateTradeForm
import com.shurdev.trade.screens.createTrade.models.CreateTradeFormValidationError
import com.shurdev.ui_kit.utils.compressImage
import com.shurdev.ui_kit.utils.toBase64
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

    init {
        updateFormData {
            it.copy(
                plantToGive = null
            )
        }
    }

    override fun sendForm() {

        val plantToGive = formData.plantToGive ?: return

        viewModelScope.launch {

            runSuspendCatching {
                tradeRepository.createTrade(
                    trade = CreateTradeModel(
                        id = 0,
                        plantToGetName = formData.plantToGetName,
                        plantToGiveName = plantToGive.name,
                        plantToGiveImage = plantToGive.imageData?.compressImage(75)?.toBase64()
                            ?: "",
                        city = formData.city,
                        description = formData.description,
                    )
                )

            }.onFailure { e ->

                updateFormData {
                    it.copy(
                        errorMessage = e.message
                    )
                }
            }
        }
    }

    fun handleError(errorMessage: String, handler: (String) -> Unit) {
        handler(errorMessage)
        updateFormData { it.copy(errorMessage = null) }
    }

    fun updateCity(newCity: String) {
        updateFormData {
            it.copy(city = newCity)
        }
    }

    fun updateContactData(newContactData: String) {
        updateFormData {
            it.copy(contactData = newContactData)
        }
    }

    fun updateAuthorName(newAuthorName: String) {
        updateFormData {
            it.copy(authorName = newAuthorName)
        }
    }

    fun updatePlantToGive(newPlantToGive: MyPlantPresentation) {
        updateFormData {
            it.copy(
                plantToGive = newPlantToGive
            )
        }
    }

    fun updatePlantToGetName(newPlantToGetName: String) {
        updateFormData {
            it.copy(
                plantToGetName = newPlantToGetName
            )
        }
    }
}