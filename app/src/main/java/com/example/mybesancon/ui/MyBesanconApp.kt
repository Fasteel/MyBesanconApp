package com.example.mybesancon.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyBesanconApp(navController: NavHostController = rememberNavController()) {
    val viewModel = viewModel<SuggestionViewModel>()

    Scaffold(topBar = {
        MyBesanconTopBar(
            navController = navController,
            uiState = viewModel.uiState
        )
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyBesanconScreen.Home.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = MyBesanconScreen.Home.name) {
                HomeScreen(
                    navController = navController,
                    suggestionCategories = viewModel.categories
                )
            }
            composable(route = "${MyBesanconScreen.List.name}/{categoryId}") { navBackStackEntry ->
                SuggestionListScreen(
                    navController = navController,
                    navBackStackEntry = navBackStackEntry,
                    uiState = viewModel.uiState
                )
            }
            composable(route = "${MyBesanconScreen.Detail.name}/{suggestionId}") { navBackStackEntry ->
                SuggestionDetailScreen(
                    navBackStackEntry = navBackStackEntry,
                    uiState = viewModel.uiState
                )
            }
        }
    }
}
