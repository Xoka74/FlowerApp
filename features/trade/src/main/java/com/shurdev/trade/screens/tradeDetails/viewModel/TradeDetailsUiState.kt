package com.shurdev.trade.screens.tradeDetails.viewModel

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.trade.Trade

@Stable
sealed class TradeDetailsUiState

@Stable
data object TradeDetailsLoadingState : TradeDetailsUiState()

@Stable
data class TradeDetailsLoadedState(
    val trade: Trade,
    val shouldDisplayConfirmButton: Boolean,
    val errorMessage: String? = null
) : TradeDetailsUiState()

@Stable
data object TradeDetailsLoadingErrorState : TradeDetailsUiState()
