package com.shurdev.trade.screens.trade.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.TradeRepository
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TradeViewModel @Inject constructor(
    private val tradeRepository: TradeRepository,
) : BaseViewModel<TradeUiState>(TradeLoadingState) {

    init{
        loadTrades()
    }

    private fun loadTrades(){
        updateUiState { TradeLoadingState }
        viewModelScope.launch {
            runSuspendCatching {
                val trades = tradeRepository.getTrades()
                updateUiState { TradeLoadedState(trades) }

            }.onFailure {
                updateUiState { TradeLoadingErrorState }
            }
        }
    }
}