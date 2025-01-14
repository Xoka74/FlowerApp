package com.shurdev.ui_kit.viewModel.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<TState>(
    initialState: TState,
) : ViewModel() {
    private val _uiState = MutableStateFlow(initialState)

    val uiState = _uiState.asStateFlow()

    protected fun updateUiState(function: (TState) -> TState) = _uiState.update(function)
}