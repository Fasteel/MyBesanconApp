package com.example.mybesancon.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mybesancon.R
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SuggestionListScreen(
    navBackStackEntry: NavBackStackEntry,
    navController: NavHostController = rememberNavController(),
    uiState: StateFlow<SuggestionUiState>
) {
    if (navBackStackEntry.arguments?.getString("categoryId") == null) {
        throw Exception("You should give the category for the SuggestionList Screen")
    }

    val categoryId =
        rememberSaveable {
            navBackStackEntry.arguments?.getString("categoryId")!!.toInt()
        }
    val suggestions =
        uiState.collectAsState().value.suggestionDetail.filter { it.category.id == categoryId }

    if (suggestions.isEmpty()) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.big)))
            CircularProgressIndicator()
        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(space = 12.dp),
        modifier = Modifier.padding(dimensionResource(R.dimen.medium))
    ) {
        items(suggestions) {
            SuggestionItem(
                title = it.title,
                mainPicture = it.mainPicture,
                description = it.description,
                onClickItem = { navController.navigate("${MyBesanconScreen.Detail.name}/${it.id}") }
            )
        }
    }

}

