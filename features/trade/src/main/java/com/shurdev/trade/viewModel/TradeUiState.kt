package com.shurdev.trade.viewModel

import androidx.compose.runtime.Stable
import com.shurdev.domain.models.trade.Trade

@Stable
sealed class TradeUiState

@Stable
data object TradeLoadingState : TradeUiState()

@Stable
data class TradeLoadedState(val trades: List<Trade>) : TradeUiState()

@Stable
data object TradeLoadingErrorState : TradeUiState()
