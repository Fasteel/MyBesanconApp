package com.example.mybesancon.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import com.example.mybesancon.R
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
        Column(
            modifier = modifier
                .padding(top = dimensionResource(R.dimen.medium))
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                modifier = modifier.fillMaxWidth(),
                painter = painterResource(suggestion.mainPicture),
                contentDescription = stringResource(R.string.image_of_suggestion),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = stringResource(suggestion.content),
                modifier = modifier.padding(dimensionResource(R.dimen.medium))
            )
        }
    }
}