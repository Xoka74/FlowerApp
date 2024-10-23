package com.shurdev.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shurdev.domain.models.Flower
import com.shurdev.domain.repositories.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    flowersRepository: FlowerRepository,
) : ViewModel() {

    private var _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val uiState: StateFlow<HomeUiState> = flowersRepository.flowers
        .combine(_searchText) { flowers, text ->
            flowers.filter { flower ->
                doesMatchSearchQuery(flower, text)
            }
        }
        .map<List<Flower>, HomeUiState> { HomeLoadedState(it) }
        .catch { emit(HomeLoadingErrorState) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeLoadingState
        )

    private var _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private fun doesMatchSearchQuery(flower: Flower, query: String): Boolean{
        return flower.name.contains(query, ignoreCase = true)
                || flower.description.contains(query, ignoreCase = true)
    }
}