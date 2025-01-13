package com.example.mybesancon.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybesancon.data.SuggestionDetail
import com.example.mybesancon.data.local.LocalSuggestionCategoryDataProvider
import com.example.mybesancon.data.remote.RemoteSuggestionDetailDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SuggestionUiState(
    val suggestionDetail: List<SuggestionDetail> = emptyList(),
    val favoriteSuggestions: List<SuggestionDetail> = emptyList()
)

class SuggestionViewModel : ViewModel() {
    // categories should maybe live outside of the view model since it's not mutated and it's static data
    val categories = LocalSuggestionCategoryDataProvider.data

    private val _uiState = MutableStateFlow(SuggestionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(suggestionDetail = RemoteSuggestionDetailDataProvider.fetch())
            }
        }
    }

    fun toggleFavorite(suggestion: SuggestionDetail) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                if (currentState.favoriteSuggestions.contains(suggestion)) {
                    currentState.copy(
                        favoriteSuggestions = currentState.favoriteSuggestions.filter { it.id != suggestion.id }
                    )
                } else {
                    currentState.copy(
                        favoriteSuggestions = currentState.favoriteSuggestions + suggestion
                    )
                }
            }
        }
    }
}