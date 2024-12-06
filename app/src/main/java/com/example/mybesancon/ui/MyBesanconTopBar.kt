package com.example.mybesancon.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mybesancon.R
import com.example.mybesancon.data.local.LocalSuggestionCategoryDataProvider
import com.example.mybesancon.data.local.LocalSuggestionDetailDataProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBesanconTopBar(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyBesanconScreen.valueOf(
        navBackStackEntry?.destination?.route?.split("/")?.get(0)
            ?: navBackStackEntry?.destination?.route
            ?: MyBesanconScreen.Home.name
    )
    val canNavigateBack = currentScreen != MyBesanconScreen.Home

    TopAppBar(
        title = {
            Column {
                Text(
                    text = stringResource(currentScreen.title),
                    style = MaterialTheme.typography.headlineMedium
                )
                when (currentScreen) {
                    MyBesanconScreen.List -> {
                        val currentCategoryId =
                            navBackStackEntry?.arguments?.getString("categoryId")!!.toInt()
                        val category =
                            LocalSuggestionCategoryDataProvider.data.find { it.id == currentCategoryId }
                        category?.let {
                            Text(
                                text = stringResource(it.title),
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }

                    MyBesanconScreen.Detail -> {
                        val suggestionId =
                            navBackStackEntry?.arguments?.getString("suggestionId")!!.toInt()
                        val suggestion =
                            LocalSuggestionDetailDataProvider.data.find { it.id == suggestionId }
                        suggestion?.let {
                            Text(
                                text = stringResource(it.title),
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }

                    else -> {
                        Text(
                            text = stringResource(R.string.categories),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}