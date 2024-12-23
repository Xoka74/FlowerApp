package com.shurdev.trade.screens.createTrade.models

import com.shurdev.domain.models.plant.Plant
import com.shurdev.ui_kit.viewModel.trackChanges.TrackChangesForm

data class CreateTradeForm(
    val plantToGive: Plant? = null,
    val plantToGet: Plant? = null,
    val city: String = "",
    val authorName: String = "",
) : TrackChangesForm<CreateTradeFormValidationError>() {

    override fun validate(): CreateTradeFormValidationError? {
        return null
    }

    override fun hasChanges(other: TrackChangesForm<CreateTradeFormValidationError>): Boolean {
        return this != other
    }
}