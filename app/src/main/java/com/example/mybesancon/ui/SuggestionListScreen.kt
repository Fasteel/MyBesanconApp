package com.example.mybesancon.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mybesancon.R
import com.example.mybesancon.data.local.LocalSuggestionDetailDataProvider

@Composable
fun SuggestionListScreen(
    navBackStackEntry: NavBackStackEntry,
    navController: NavHostController = rememberNavController(),
) {
    if (navBackStackEntry.arguments?.getString("categoryId") == null) {
        throw Exception("You should give the category for the SuggestionList Screen")
    }

    val categoryId =
        rememberSaveable {
            navBackStackEntry.arguments?.getString("categoryId")!!.toInt()
        }
    val suggestions = LocalSuggestionDetailDataProvider.data.filter { it.category.id == categoryId }

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

