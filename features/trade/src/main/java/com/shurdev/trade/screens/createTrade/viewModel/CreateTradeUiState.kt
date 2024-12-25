package com.shurdev.trade.screens.createTrade.viewModel

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.trade.Trade

@Stable
sealed class CreateTradeUiState

@Stable
data object CreateTradeLoadingState : CreateTradeUiState()

@Stable
data class CreateTradeLoadedState(val trade: Trade) : CreateTradeUiState()

@Stable
data object CreateTradeLoadingErrorState : CreateTradeUiState()

