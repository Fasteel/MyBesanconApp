package com.example.mybesancon.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybesancon.R
import com.example.mybesancon.data.SuggestionDetail
import com.example.mybesancon.data.local.LocalSuggestionCategoryDataProvider
import com.example.mybesancon.ui.theme.AppTheme

@Composable
fun SuggestionItem(
    @StringRes title: Int,
    @DrawableRes mainPicture: Int,
    @StringRes description: Int,
    onClickItem: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(150.dp)
            .fillMaxWidth()
            .clickable { onClickItem() }
    ) {
        Row {
            Image(
                painter = painterResource(mainPicture),
                contentDescription = stringResource(title),
                modifier = modifier.weight(1f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = modifier
                    .padding(dimensionResource(R.dimen.small))
                    .weight(2f)
            ) {
                Text(
                    text = stringResource(title),
                    modifier = modifier.padding(bottom = dimensionResource(R.dimen.small)),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = stringResource(description).substring(0, 130) + "...",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Preview
@Composable
fun SuggestionItemPreview() {
    AppTheme {
        val item = SuggestionDetail(
            id = 1,
            title = R.string.roaming_via_francigena_title,
            content = R.string.roaming_via_francigena_content,
            category = LocalSuggestionCategoryDataProvider.data[0],
            description = R.string.roaming_via_francigena_description,
            mainPicture = R.drawable.viafrancigena
        )

        SuggestionItem(
            title = item.title,
            mainPicture = item.mainPicture,
            description = item.description,
            onClickItem = {}
        )
    }
}
