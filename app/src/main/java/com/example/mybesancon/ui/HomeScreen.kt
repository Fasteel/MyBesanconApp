package com.example.mybesancon.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mybesancon.R
import com.example.mybesancon.data.local.LocalSuggestionCategoryDataProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    val categories = rememberSaveable { LocalSuggestionCategoryDataProvider.data }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val carouselState = rememberCarouselState { categories.count() }

            HorizontalMultiBrowseCarousel(
                state = carouselState,
                itemSpacing = dimensionResource(R.dimen.medium),
                contentPadding = PaddingValues(start = dimensionResource(R.dimen.medium)),
                preferredItemWidth = 250.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        top = dimensionResource(R.dimen.small),
                        bottom = dimensionResource(R.dimen.small)
                    )
            ) { index ->
                val value = categories[index]

                Column(modifier = Modifier.clickable {
                    navController.navigate(
                        "${MyBesanconScreen.List.name}/${value.id}"
                    )
                }) {
                    Box(contentAlignment = Alignment.BottomCenter) {
                        Image(
                            painter = painterResource(id = value.mainPicture),
                            contentDescription = stringResource(value.title),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(350.dp)
                                .fillMaxWidth()
                                .maskClip(shape = MaterialTheme.shapes.extraLarge)
                        )
                        Card(
                            modifier = Modifier.padding(bottom = 25.dp),
                            colors = CardDefaults.cardColors()
                                .copy(
                                    containerColor = CardDefaults.cardColors().containerColor.copy(
                                        alpha = 0.7f
                                    )
                                )
                        ) {
                            Text(
                                modifier = Modifier.padding(dimensionResource(R.dimen.small)),
                                text = stringResource(value.title),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                    }
                }
            }
        }
    }
}