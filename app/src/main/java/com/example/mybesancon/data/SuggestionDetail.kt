package com.example.mybesancon.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SuggestionDetail(
    val id: Int,
    val category: SuggestionCategory,
    @StringRes val title: Int,
    @StringRes val content: Int,
    @DrawableRes val mainPicture: Int,
)
