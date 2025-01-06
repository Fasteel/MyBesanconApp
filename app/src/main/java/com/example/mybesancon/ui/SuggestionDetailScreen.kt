package com.example.mybesancon.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SuggestionDetailScreen(
    navBackStackEntry: NavBackStackEntry,
    modifier: Modifier = Modifier,
    uiState: StateFlow<SuggestionUiState>
) {
    val suggestionId = rememberSaveable {
        navBackStackEntry.arguments?.getString("suggestionId")!!.toInt()
    }
    val suggestion =
        uiState.collectAsState().value.suggestionDetail.find { it.id == suggestionId }

    if (suggestion != null) {
        SuggestionDetail(
            mainPicture = suggestion.mainPicture,
            content = suggestion.content
        )
    }
}