package com.example.mybesancon.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.mybesancon.R

@Composable
fun SuggestionDetail(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    @StringRes content: Int,
    mainPicture: Int
) {
    Column(
        modifier = modifier
            .padding(top = dimensionResource(R.dimen.medium))
            .verticalScroll(state = scrollState)
    ) {
        Image(
            modifier = modifier.fillMaxWidth(),
            painter = painterResource(mainPicture),
            contentDescription = stringResource(R.string.image_of_suggestion),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = stringResource(content),
            modifier = modifier.padding(dimensionResource(R.dimen.medium))
        )
    }

}