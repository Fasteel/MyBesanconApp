package com.example.mybesancon.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.mybesancon.R
import com.example.mybesancon.data.SuggestionDetail
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun SuggestionListScreen(
    navBackStackEntry: NavBackStackEntry,
    navController: NavHostController = rememberNavController(),
    uiState: StateFlow<SuggestionUiState>,
    onSaveFavorite: (suggestion: SuggestionDetail) -> Unit
) {
    if (navBackStackEntry.arguments?.getString("categoryId") == null) {
        throw Exception("You should give the category for the SuggestionList Screen")
    }

    val categoryId =
        rememberSaveable {
            navBackStackEntry.arguments?.getString("categoryId")!!.toInt()
        }
    val suggestions =
        uiState.collectAsState().value.suggestionDetail.filter { it.category.id == categoryId.absoluteValue }

    if (suggestions.isEmpty()) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.big)))
            CircularProgressIndicator()
        }

        return
    }

    val canShowSideSuggestionDetail =
        currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT

    if (canShowSideSuggestionDetail) {
        val selectedSuggestionId =
            rememberSaveable { mutableIntStateOf(uiState.value.suggestionDetail.first { categoryId == it.category.id }.id) }
        // how to avoid collecting the state, is it possible?
        val selectedSuggestion =
            uiState.collectAsState().value.suggestionDetail.first { it.id == selectedSuggestionId.intValue }
        val coroutineScope = rememberCoroutineScope()
        val scrollState = rememberScrollState()

        Row {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(space = 12.dp),
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.medium))
                    .weight(1f)
            ) {
                items(suggestions) {
                    SuggestionItem(
                        title = it.title,
                        mainPicture = it.mainPicture,
                        description = it.description,
                        onClickItem = {
                            selectedSuggestionId.intValue = it.id
                            coroutineScope.launch {
                                scrollState.scrollTo(0)
                            }
                        },
                        isFavorite = it.isFavorite,
                        onSaveFavorite = { onSaveFavorite(it) }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen.medium),
                        end = dimensionResource(R.dimen.medium)
                    )
                    .weight(1f)
            ) {
                SuggestionDetail(
                    scrollState = scrollState,
                    mainPicture = selectedSuggestion.mainPicture,
                    content = selectedSuggestion.content
                )
            }
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(space = 12.dp),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.medium))
        ) {
            items(suggestions) {
                SuggestionItem(
                    title = it.title,
                    mainPicture = it.mainPicture,
                    description = it.description,
                    isFavorite = it.isFavorite,
                    onClickItem = { navController.navigate("${MyBesanconScreen.Detail.name}/${it.id}") },
                    onSaveFavorite = { onSaveFavorite(it) }
                )
            }
        }
    }
}

