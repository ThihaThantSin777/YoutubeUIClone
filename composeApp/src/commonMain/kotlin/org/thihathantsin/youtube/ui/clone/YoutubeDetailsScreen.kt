package org.thihathantsin.youtube.ui.clone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import org.jetbrains.compose.resources.painterResource
import youtubeuiclone.composeapp.generated.resources.Res
import youtubeuiclone.composeapp.generated.resources.arrow_back_icon
import youtubeuiclone.composeapp.generated.resources.download_icon
import youtubeuiclone.composeapp.generated.resources.like_icon
import youtubeuiclone.composeapp.generated.resources.play_arrow_icon
import youtubeuiclone.composeapp.generated.resources.share_icon
import youtubeuiclone.composeapp.generated.resources.unlike_icon
import youtubeuiclone.composeapp.generated.resources.youtube_short_video_select_icon

@Composable
fun YoutubeDetailsPage(navHostController: NavHostController) {
    Scaffold(
        containerColor = Color.White,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            item {
                YoutubeVideoThumbnail(navHostController)
            }


            item {
                YoutubeVideoInfoSection()
            }


            item {
                YoutubeChannelSection()
            }


            item {
                YoutubeActionButtonsSection()
            }


            item {
                YoutubeCommentsSection()
            }

            items(40) {
                YoutubeContentView()
            }
        }
    }
}


@Composable
fun YoutubeVideoThumbnail(navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
    ) {
        SubcomposeAsyncImage(
            model = "https://i.ytimg.com/vi/FNj3KjZd7Ic/maxresdefault.jpg",
            contentDescription = "Video Thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
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
                    Text("Failed to load", color = Color.White)
                }
            }
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(60.dp)
                .background(Color.Black.copy(alpha = 0.7f), CircleShape)
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(Res.drawable.play_arrow_icon),
                contentDescription = "Play",
                modifier = Modifier.size(30.dp)
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(60.dp)
                .padding(10.dp)
                .clickable {
                    navHostController.popBackStack()
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(Res.drawable.arrow_back_icon),
                contentDescription = "Arrow Back",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun YoutubeVideoInfoSection() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            text = "Naruto Uses Yin and Yang Chakra To Restore Kakashi's Eye - Naruto Gains Po...",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(8.dp))


        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "3.3M views",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "11mo ago",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "...more",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.clickable { }
            )
        }
    }
}

@Composable
fun YoutubeChannelSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        SubcomposeAsyncImage(
            model = "https://m.media-amazon.com/images/M/MV5BNTk3MDA1ZjAtNTRhYS00YzNiLTgwOGEtYWRmYTQ3NjA0NTAwXkEyXkFqcGc@._V1_.jpg",
            contentDescription = "Channel Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            loading = {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Gray.copy(alpha = 0.3f), CircleShape)
                )
            }
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = "Anime world",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "34.6K",
            fontSize = 12.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.weight(1F))

        Box(
            modifier = Modifier
                .background(Color.Black, RoundedCornerShape(20.dp))
                .clickable { }
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Subscribe",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun YoutubeActionButtonsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp)).background(Color.Gray.copy(alpha = 0.2F))
        ) {
            YoutubeActionButton(
                iconRes = Res.drawable.like_icon,
                text = "19K",
                onClick = { }
            )
        }


        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp)).background(Color.Gray.copy(alpha = 0.2F))
        ) {
            YoutubeActionButton(
                iconRes = Res.drawable.share_icon,
                text = "Share",
                onClick = { }
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp)).background(Color.Gray.copy(alpha = 0.2F))
        ) {
            YoutubeActionButton(
                iconRes = Res.drawable.youtube_short_video_select_icon,
                text = "Remix",
                onClick = { }
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp)).background(Color.Gray.copy(alpha = 0.2F))
        ) {
            YoutubeActionButton(
                iconRes = Res.drawable.download_icon,
                text = "Download",
                onClick = { }
            )
        }
    }
}

@Composable
fun YoutubeActionButton(
    iconRes: org.jetbrains.compose.resources.DrawableResource,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp).clickable { onClick() }
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = text,
            modifier = Modifier.size(24.dp)
        )
        if (text.isNotEmpty()) {
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = text,
                fontSize = 13.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun YoutubeCommentsSection() {
    Box(
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(10.dp)).background(Color.Gray.copy(alpha = 0.2F))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Comments",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "385",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            YoutubeCommentItem()
        }
    }
}

@Composable
fun YoutubeCommentItem() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        SubcomposeAsyncImage(
            model = "https://yt3.ggpht.com/a/default-user=s28-c-k-c0x00ffffff-no-rj",
            contentDescription = "Commenter Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape),
            loading = {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color.Gray.copy(alpha = 0.3f), CircleShape)
                )
            }
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "This is why I hate boruto so much, the way they nerfed Sasuke & Naruto, those two are literally gods here, m...",
                fontSize = 14.sp,
                color = Color.Black,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { }
                ) {
                    Image(
                        painterResource(Res.drawable.like_icon),
                        contentDescription = "Like",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "12",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { }
                ) {
                    Image(
                        painterResource(Res.drawable.unlike_icon),
                        contentDescription = "Dislike",
                        modifier = Modifier
                            .size(16.dp)
                            .clickable { }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "12",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }



                Text(
                    text = "Reply",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.clickable { }
                )
            }
        }
    }
}