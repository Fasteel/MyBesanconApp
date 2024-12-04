package com.example.mybesancon.data.local

import com.example.mybesancon.R
import com.example.mybesancon.data.SuggestionCategory

object LocalSuggestionCategoryDataProvider {
    val data = listOf(
        SuggestionCategory(
            id = 1,
            title = R.string.outdoor_activity,
            mainPicture = R.drawable.outdoor_activity
        ),
        SuggestionCategory(
            id = 2,
            title = R.string.gastronomy,
            mainPicture = R.drawable.gastronomy
        ),
        SuggestionCategory(
            id = 3,
            title = R.string.culture,
            mainPicture = R.drawable.culture
        ),
    )
}