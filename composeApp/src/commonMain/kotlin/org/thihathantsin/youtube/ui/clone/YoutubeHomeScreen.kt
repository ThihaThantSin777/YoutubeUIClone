package org.thihathantsin.youtube.ui.clone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import org.jetbrains.compose.resources.painterResource
import youtubeuiclone.composeapp.generated.resources.Res
import youtubeuiclone.composeapp.generated.resources.close_caption_icon
import youtubeuiclone.composeapp.generated.resources.explore_icon
import youtubeuiclone.composeapp.generated.resources.notification_icon
import youtubeuiclone.composeapp.generated.resources.open_caption_icon
import youtubeuiclone.composeapp.generated.resources.screen_cast_icon
import youtubeuiclone.composeapp.generated.resources.search_icon
import youtubeuiclone.composeapp.generated.resources.volume_mute_icon
import youtubeuiclone.composeapp.generated.resources.volume_up_icon
import youtubeuiclone.composeapp.generated.resources.youtube_logo

@Composable
fun YoutubeHomeScreen() {
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
            YoutubeHomeContent()
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
fun YoutubeHomeContent() {
    LazyColumn {

        item {
            YoutubeGenreSection()
        }
        items(40) {
            YoutubeVideoItem()
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
                        text = "Game",
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


@Composable
fun YoutubeVideoItem() {

    Box(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Column {
            Box(modifier = Modifier.weight(1f)) {
                YoutubeThumbnailView()
                YoutubeControlView(
                    Modifier.align(
                        Alignment.TopEnd,
                    ).padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
            Box(
                modifier = Modifier
                    .weight(0.3F)
                    .background(Color.Red)
            ) {
                Text(
                    text = "This is the title of the video. This is a sample title.This is the title of the video. This is a sample title.This is the title of the video. This is a sample title.",
                    maxLines = 2,
                )
            }
        }

    }
}

@Composable
fun YoutubeControlView(modifier: Modifier) {
    var isVolumeOn by remember {
        mutableStateOf(true)
    }
    var isCaptionOn by remember {
        mutableStateOf(true)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp), modifier = modifier,
    ) {
        Box(
            modifier = Modifier.clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.5f)).clickable{
                    isVolumeOn=!isVolumeOn
                }
        ) {
            if (isVolumeOn)
                Image(
                    painterResource(Res.drawable.volume_up_icon),
                    contentDescription = "Volume Up Icon",
                    modifier = Modifier
                        .size(45.dp, 45.dp).padding(10.dp)
                )
            else
                Image(
                    painterResource(Res.drawable.volume_mute_icon),
                    contentDescription = "Volume Mute Icon",
                    modifier = Modifier
                        .size(45.dp, 45.dp).padding(10.dp)
                )
        }
        Box(
            modifier = Modifier.clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.5f)).clickable{
                    isCaptionOn=!isCaptionOn
                }
        ) {
            if (isCaptionOn)
            Image(
                painterResource(Res.drawable.open_caption_icon),
                contentDescription = "Open Caption Icon",
                modifier = Modifier
                    .size(45.dp, 45.dp).padding(10.dp)
            )
            else
            Image(
                painterResource(Res.drawable.close_caption_icon),
                contentDescription = "Close Caption Icon",
                modifier = Modifier
                    .size(45.dp, 45.dp).padding(10.dp)
            )
        }
    }
}

@Composable
fun YoutubeThumbnailView() {
    SubcomposeAsyncImage(
        model = "https://marketplace.canva.com/EAEqfS4X0Xw/1/0/1600w/canva-most-attractive-youtube-thumbnail-wK95f3XNRaM.jpg",
        contentDescription = "Video Thumbnail",
        contentScale = ContentScale.Crop,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        error = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Failed to load image",
                    color = Color.White,
                )
            }
        }
    )

}
