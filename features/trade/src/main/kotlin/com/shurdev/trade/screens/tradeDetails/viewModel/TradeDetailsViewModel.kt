package com.shurdev.trade.screens.tradeDetails.viewModel

import androidx.lifecycle.viewModelScope
import com.shurdev.domain.repositories.TradeRepository
import com.shurdev.ui_kit.viewModel.base.BaseViewModel
import com.shurdev.utils.runSuspendCatching
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = TradeDetailsViewModel.ViewModelFactory::class)
class TradeDetailsViewModel @AssistedInject constructor(
    @Assisted private val tradeId: Int,
    private val tradeRepository: TradeRepository
) : BaseViewModel<TradeDetailsUiState>(TradeDetailsLoadingState) {

    init {
        loadTrade(tradeId = tradeId)
    }

    fun confirmTrade() {
        viewModelScope.launch {
            runSuspendCatching {
                tradeRepository.confirmTrade(tradeId)
            }.onFailure { exception ->
                setErrorMessage(exception.message)
            }
        }
    }

    fun clearErrorMessage() {
        setErrorMessage(null)
    }

    private fun setErrorMessage(errorMessage: String?) {
        loadedState?.let { state ->
            updateUiState { state.copy(errorMessage = errorMessage) }
        }
    }

    private fun loadTrade(tradeId: Int) {
        viewModelScope.launch {
            updateUiState { TradeDetailsLoadingState }

            runSuspendCatching {
                val trade = tradeRepository.getTradeById(tradeId)

                if (trade != null) {
                    updateUiState {
                        TradeDetailsLoadedState(
                            trade = trade,
                            shouldDisplayConfirmButton = true // TODO
                        )
                    }
                } else {
                    updateUiState { TradeDetailsLoadingErrorState }
                }

            }.onFailure {
                updateUiState { TradeDetailsLoadingErrorState }
            }
        }
    }

    private val loadedState: TradeDetailsLoadedState?
        get() = uiState.value as? TradeDetailsLoadedState

    @AssistedFactory
    interface ViewModelFactory {

        fun create(tradeId: Int): TradeDetailsViewModel
    }
}