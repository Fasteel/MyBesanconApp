package com.example.mybesancon.ui

import androidx.annotation.StringRes
import com.example.mybesancon.R

enum class MyBesanconScreen(@StringRes val title: Int) {
    Home(R.string.app_name),
    List(R.string.suggestion_list),
    Detail(R.string.suggestion_detail),
}
