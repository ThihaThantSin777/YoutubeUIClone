package org.thihathantsin.youtube.ui.clone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.jetbrains.compose.resources.painterResource
import youtubeuiclone.composeapp.generated.resources.Res
import youtubeuiclone.composeapp.generated.resources.explore_icon
import youtubeuiclone.composeapp.generated.resources.notification_icon
import youtubeuiclone.composeapp.generated.resources.screen_cast_icon
import youtubeuiclone.composeapp.generated.resources.search_icon
import youtubeuiclone.composeapp.generated.resources.youtube_logo

@Composable
fun YoutubeHomeScreen(navHostController: NavHostController) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            YoutubeAppBarContent(
                onTapScreenCast = {

                },
                onTapNotification = {

                },
                onTapSearch = {

                },
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            YoutubeHomeContent(navHostController)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YoutubeAppBarContent(
    onTapScreenCast: () -> Unit = {},
    onTapNotification: () -> Unit = {},
    onTapSearch: () -> Unit = {},
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
        ),
        title = {
            Image(
                painterResource(Res.drawable.youtube_logo),
                contentDescription = "Youtube Logo",
            )
        },
        actions = {
            Row(horizontalArrangement = Arrangement.spacedBy(25.dp)) {
                Image(
                    painterResource(Res.drawable.screen_cast_icon),
                    contentDescription = "Screen Cast Logo",
                    modifier = Modifier.size(25.dp, 25.dp).clickable {
                        onTapScreenCast()
                    },
                )
                Image(
                    painterResource(Res.drawable.notification_icon),
                    contentDescription = "Screen Cast Logo",
                    modifier = Modifier.size(25.dp, 25.dp).clickable {
                        onTapNotification()
                    },
                )
                Image(
                    painterResource(Res.drawable.search_icon),
                    contentDescription = "Screen Cast Logo",
                    modifier = Modifier.padding(end = 10.dp).size(25.dp, 25.dp).clickable {
                        onTapSearch()
                    },
                )
            }
        }
    )
}

@Composable
fun YoutubeHomeContent(navHostController: NavHostController) {
    LazyColumn {

        item {
            YoutubeGenreSection()
        }
        items(40) {
            YoutubeContentView(
                onVideoClick = {
                    navHostController.navigate(YoutubeDestinations.VIDEO_DETAILS)

                }
            )
        }
    }
}


@Composable
fun YoutubeGenreSection() {
    LazyRow(contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)) {
        item {
            YoutubeGenreBox(
                content = {
                    Image(
                        painterResource(Res.drawable.explore_icon),
                        contentDescription = "Explore Icon",
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)
                            .size(30.dp, 30.dp),
                    )
                }
            )
        }
        items(20) {
            YoutubeGenreBox(
                isSelect = it == 0,
                content = {
                    Text(
                        text = "Anime",
                        color = if (it == 0) Color.White else Color.Black,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)
                    )
                }
            )
        }
    }
}


@Composable
fun YoutubeGenreBox(
    content: @Composable () -> Unit,
    isSelect: Boolean = false,
    onTapGenreBox: () -> Unit = {}
) {
    Box(
        modifier = Modifier.padding(end = 10.dp)
            .background(
                if (isSelect) Color.Black else
                    Color(243, 243, 243), shape = RoundedCornerShape(10.dp)
            ).clickable {
                onTapGenreBox()
            }
    ) {
        content()
    }
}

