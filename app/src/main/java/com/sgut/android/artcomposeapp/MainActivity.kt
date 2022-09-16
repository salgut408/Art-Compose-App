package com.sgut.android.artcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sgut.android.artcomposeapp.artworkdetail.ArtworkDetailScreen
import com.sgut.android.artcomposeapp.artworklists.ArtworkListScreen
import com.sgut.android.artcomposeapp.ui.theme.ArtComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtComposeAppTheme {
            val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "artwork_list_screen" ) {
                    composable("artwork_list_screen") {
                        ArtworkListScreen(navController = navController)
                    }
                    composable("artwork_detail_screen/{dominantColor}/{artworkId}",
                        arguments = listOf(
                            navArgument("dominantColor") {
                                type = NavType.IntType
                            },
                            navArgument("artworkId") {
                                type = NavType.IntType
                            }
                        )
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt("dominantColor")
                            color?.let { Color(it) ?: Color.White }
                        }
                        val artworkId = remember {
                            it.arguments?.getInt("artworkId")
                        }
                        if (artworkId != null) {
                            if (dominantColor != null) {
                                ArtworkDetailScreen(
                                    dominantColor = dominantColor,
                                    artworkId = artworkId,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

