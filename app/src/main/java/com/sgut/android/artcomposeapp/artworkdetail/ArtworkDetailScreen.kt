package com.sgut.android.artcomposeapp.artworkdetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ArtworkDetailScreen(
    dominantColor: Color,
    artworkId: Int,
    navController: NavController,
    topPadding: Dp = 20.dp,
    artworkImageSize: Dp = 200.dp,
    viewModel: ArtworkDetailViewModel = hiltViewModel(),

    ) {

}