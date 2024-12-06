package com.example.mybesancon.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuggestionCategory(
    val id: Int,
    @StringRes val title: Int,
    @DrawableRes val mainPicture: Int,
) : Parcelable
