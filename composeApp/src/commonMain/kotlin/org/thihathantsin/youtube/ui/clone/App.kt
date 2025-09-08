package org.thihathantsin.youtube.ui.clone

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

object YoutubeDestinations {
    const val HOME = "home"
    const val VIDEO_DETAILS = "video_details"
}

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = YoutubeDestinations.HOME
        ) {
            composable(YoutubeDestinations.HOME) {
                YoutubeHomeScreen(
                    navController
                )
            }

            composable(YoutubeDestinations.VIDEO_DETAILS) {
                YoutubeDetailsPage(
                    navController
                )
            }
        }
    }
}