package com.shurdev.trade.screens.createTrade.models

import com.shurdev.trade.models.MyPlantPresentation
import com.shurdev.ui_kit.viewModel.trackChanges.TrackChangesForm

data class CreateTradeForm(
    val plantToGive: MyPlantPresentation? = null,
    val plantToGetName: String = "",
    val city: String = "",
    val authorName: String = "",
    val contactData: String = "",
    val description: String = "",
    val errorMessage: String? = null
) : TrackChangesForm<CreateTradeFormValidationError>() {

    override fun validate(): CreateTradeFormValidationError? {
        return null
    }

    override fun hasChanges(other: TrackChangesForm<CreateTradeFormValidationError>): Boolean {
        return this != other
    }
}