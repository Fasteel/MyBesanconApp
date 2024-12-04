package com.example.mybesancon.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SuggestionCategory(
    val id: Int,
    @StringRes val title: Int,
    @DrawableRes val mainPicture: Int,
)
