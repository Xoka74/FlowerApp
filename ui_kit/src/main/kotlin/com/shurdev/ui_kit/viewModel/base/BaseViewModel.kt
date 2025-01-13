package com.shurdev.ui_kit.viewModel.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<TState>(
    initialState: TState,
) : ViewModel(), StateHolder<TState> {
    private val _uiState = MutableStateFlow(initialState)

    override val uiState = _uiState.asStateFlow()

    override fun updateUiState(function: (TState) -> TState) = _uiState.update(function)
}

interface StateHolder<TState> {
    val uiState: StateFlow<TState>

    fun updateUiState(function: (TState) -> TState)
}