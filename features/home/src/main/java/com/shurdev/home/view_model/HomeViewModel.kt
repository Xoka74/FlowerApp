package com.shurdev.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.Flower
import com.shurdev.domain.repositories.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    flowersRepository: FlowerRepository,
) : ViewModel() {
    val uiState: StateFlow<HomeUiState> = flowersRepository.flowers
        .map<List<Flower>, HomeUiState> { HomeLoadedState(it) }
        .catch { emit(HomeLoadingErrorState) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeLoadingState
        )
}