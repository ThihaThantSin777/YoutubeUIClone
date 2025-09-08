package org.thihathantsin.youtube.ui.clone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import org.jetbrains.compose.resources.painterResource
import youtubeuiclone.composeapp.generated.resources.Res
import youtubeuiclone.composeapp.generated.resources.close_caption_icon
import youtubeuiclone.composeapp.generated.resources.download_icon
import youtubeuiclone.composeapp.generated.resources.more_vert_icon
import youtubeuiclone.composeapp.generated.resources.not_interested_icon
import youtubeuiclone.composeapp.generated.resources.not_recommand_icon
import youtubeuiclone.composeapp.generated.resources.open_caption_icon
import youtubeuiclone.composeapp.generated.resources.play_queue_icon
import youtubeuiclone.composeapp.generated.resources.report_icon
import youtubeuiclone.composeapp.generated.resources.save_to_playlist_icon
import youtubeuiclone.composeapp.generated.resources.save_to_watch_icon
import youtubeuiclone.composeapp.generated.resources.share_icon
import youtubeuiclone.composeapp.generated.resources.volume_mute_icon
import youtubeuiclone.composeapp.generated.resources.volume_up_icon


@Composable
fun YoutubeContentView(onVideoClick: () -> Unit = {}) {

    Box(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .height(300.dp).clickable {
                onVideoClick()
            }
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(modifier = Modifier.weight(1f)) {
                YoutubeThumbnailView()
                YoutubeControlView(
                    Modifier.align(
                        Alignment.TopEnd,
                    ).padding(horizontal = 20.dp, vertical = 10.dp)
                )
                YoutubeRuntimeView(
                    Modifier.align(
                        Alignment.BottomEnd,
                    ).padding(bottom = 10.dp, end = 10.dp)
                )
            }

            Box(
                modifier = Modifier
                    .weight(0.3F).fillMaxWidth().padding(horizontal = 15.dp)
            ) {
                YoutubeHeadlineView()
            }
        }

    }
}

@Composable
fun YoutubeHeadlineView() {
    var showBottomSheet by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        SubcomposeAsyncImage(
            model = "https://m.media-amazon.com/images/M/MV5BNTk3MDA1ZjAtNTRhYS00YzNiLTgwOGEtYWRmYTQ3NjA0NTAwXkEyXkFqcGc@._V1_.jpg",
            contentDescription = "Channel Thumbnail",
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
            },
            modifier = Modifier.clip(CircleShape).size(45.dp, 45.dp)
        )

        Column {
            Text(
                "Naruto 4th GREAT ninja war (FULL FIGHT) #english how it started to the end. #naruto #greatninjawar",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                modifier = Modifier.width(300.dp),
            )
            Text("Anime world   251 views  2 years ago", fontSize = 12.sp)
        }

        Image(
            painterResource(Res.drawable.more_vert_icon),
            contentDescription = "More Vert Icon",
            modifier = Modifier.size(30.dp).clickable {
                showBottomSheet = true
            }
        )
    }

    // Bottom Sheet
    if (showBottomSheet) {
        YoutubeMoreOptionsBottomSheet(
            onDismiss = { showBottomSheet = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YoutubeMoreOptionsBottomSheet(
    onDismiss: () -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        contentColor = Color.Black,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .width(40.dp)
                    .height(4.dp)
                    .background(Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(2.dp))
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            YoutubeBottomSheetItem(
                iconRes = Res.drawable.play_queue_icon,
                text = "Play next in queue",
                onClick = {
                    onDismiss()
                }
            )

            YoutubeBottomSheetItem(
                iconRes = Res.drawable.save_to_watch_icon,
                text = "Save to Watch later",
                onClick = {
                    onDismiss()
                }
            )

            YoutubeBottomSheetItem(
                iconRes = Res.drawable.save_to_playlist_icon,
                text = "Save to playlist",
                onClick = {
                    onDismiss()
                }
            )

            YoutubeBottomSheetItem(
                iconRes = Res.drawable.download_icon,
                text = "Download video",
                onClick = {
                    onDismiss()

                }
            )

            YoutubeBottomSheetItem(
                iconRes = Res.drawable.share_icon,
                text = "Share",
                onClick = {
                    onDismiss()
                }
            )

            YoutubeBottomSheetItem(
                iconRes = Res.drawable.not_interested_icon,
                text = "Not interested",
                onClick = {
                    onDismiss()
                }
            )

            YoutubeBottomSheetItem(
                iconRes = Res.drawable.not_recommand_icon,
                text = "Don't recommend channel",
                onClick = {
                    onDismiss()
                }
            )

            YoutubeBottomSheetItem(
                iconRes = Res.drawable.report_icon,
                text = "Report",
                onClick = {
                    onDismiss()
                }
            )
        }
    }
}

@Composable
fun YoutubeBottomSheetItem(
    iconRes: org.jetbrains.compose.resources.DrawableResource,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = text,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal
        )
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
                .background(Color.Black.copy(alpha = 0.5f)).clickable {
                    isVolumeOn = !isVolumeOn
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
                .background(Color.Black.copy(alpha = 0.5f)).clickable {
                    isCaptionOn = !isCaptionOn
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
        model = "https://fwmedia.fandomwire.com/wp-content/uploads/2025/02/12124012/naruto-4th-great-ninja-war.jpg",
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

@Composable
fun YoutubeRuntimeView(modifier: Modifier) {
    Box(
        modifier = modifier.clip(RoundedCornerShape(5.dp))
            .background(Color.Black.copy(alpha = 0.5f)).padding(horizontal = 10.dp)
    ) {
        Text("4:10:12", color = Color.White, fontSize = 13.sp)
    }
}
