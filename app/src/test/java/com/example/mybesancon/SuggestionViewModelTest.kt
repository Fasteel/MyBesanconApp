package com.example.mybesancon

import com.example.mybesancon.data.SuggestionDetail
import com.example.mybesancon.data.local.LocalSuggestionCategoryDataProvider
import com.example.mybesancon.ui.SuggestionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SuggestionViewModelTest {

    private val viewModel = SuggestionViewModel()

    @Test
    fun viewModelLoad_shouldReturnDefaultCategories() {
        val categories = viewModel.categories
        Assertions.assertEquals(categories, LocalSuggestionCategoryDataProvider.data)
    }

    @Test
    fun viewModelLoad_shouldReturnDefaultSuggestionDetails() = runTest {
        advanceUntilIdle()
        val suggestionDetail = viewModel.uiState.value.suggestionDetail
        val expectedResult = listOf(
            SuggestionDetail(
                id = 1,
                title = R.string.roaming_via_francigena_title,
                content = R.string.roaming_via_francigena_content,
                category = LocalSuggestionCategoryDataProvider.data[0],
                description = R.string.roaming_via_francigena_description,
                mainPicture = R.drawable.viafrancigena
            ),
            SuggestionDetail(
                id = 2,
                title = R.string.arcier_aqueduct_title,
                content = R.string.arcier_aqueduct_content,
                category = LocalSuggestionCategoryDataProvider.data[0],
                description = R.string.roaming_via_francigena_description,
                mainPicture = R.drawable.arcier
            ),
            SuggestionDetail(
                id = 3,
                title = R.string.bikest_title,
                content = R.string.bikest_content,
                category = LocalSuggestionCategoryDataProvider.data[0],
                description = R.string.bikest_description,
                mainPicture = R.drawable.bikest
            ),
            SuggestionDetail(
                id = 4,
                title = R.string.cheese_cellar_title,
                content = R.string.cheese_cellar_content,
                category = LocalSuggestionCategoryDataProvider.data[1],
                description = R.string.cheese_cellar_description,
                mainPicture = R.drawable.cheese_cellar
            ),
            SuggestionDetail(
                id = 5,
                title = R.string.unalome_title,
                content = R.string.unalome_content,
                category = LocalSuggestionCategoryDataProvider.data[1],
                description = R.string.unalome_description,
                mainPicture = R.drawable.unalome
            ),
            SuggestionDetail(
                id = 6,
                title = R.string.piano_pizzeria_title,
                content = R.string.piano_pizzeria_content,
                category = LocalSuggestionCategoryDataProvider.data[1],
                description = R.string.piano_pizzeria_description,
                mainPicture = R.drawable.piano_pizz
            ),
            SuggestionDetail(
                id = 7,
                title = R.string.parc_title,
                content = R.string.parc_content,
                category = LocalSuggestionCategoryDataProvider.data[1],
                description = R.string.parc_description,
                mainPicture = R.drawable.parc
            ),
            SuggestionDetail(
                id = 8,
                title = R.string.citadelle_title,
                content = R.string.citadelle_content,
                category = LocalSuggestionCategoryDataProvider.data[2],
                description = R.string.citadelle_description,
                mainPicture = R.drawable.citadelle
            ),
            SuggestionDetail(
                id = 9,
                title = R.string.resistance_title,
                content = R.string.resistance_content,
                category = LocalSuggestionCategoryDataProvider.data[2],
                description = R.string.resistance_description,
                mainPicture = R.drawable.resistance
            ),
            SuggestionDetail(
                id = 10,
                title = R.string.temps_title,
                content = R.string.temps_content,
                category = LocalSuggestionCategoryDataProvider.data[2],
                description = R.string.temps_description,
                mainPicture = R.drawable.temps
            )
        )

        Assertions.assertEquals(suggestionDetail, expectedResult)
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            val dispatcher = StandardTestDispatcher()
            Dispatchers.setMain(dispatcher)
        }

        @JvmStatic
        @AfterAll
        fun after() {
            Dispatchers.resetMain()
        }
    }
}
