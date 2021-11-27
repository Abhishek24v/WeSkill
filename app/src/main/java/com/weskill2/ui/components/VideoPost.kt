package com.weskill2.ui.components

import android.widget.LinearLayout
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.weskill2.R
import com.weskill2.ui.theme.Red


@OptIn(ExperimentalFoundationApi::class, coil.annotation.ExperimentalCoilApi::class)
@Composable
fun VideoPost(onClick: () -> Unit? = {}, exoPlayer: SimpleExoPlayer) {

    val liked = remember { mutableStateOf(false) }
    val reacted = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth()
            .combinedClickable {
                onClick()
            }
            .background(color = Color.White)
    ) {

        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp)
            ) {

                Image(
                    painter = rememberImagePainter("https://weskill.co.in/static/media/weskill_element_final.8df0e71d.png"),
                    contentDescription = "owner dp",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {

                    Row {
                        Text(
                            text = "WeSkill",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(fonts = arrayOf(Font(R.font.hind_regular)))
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_verified),
                            contentDescription = null,
                            modifier = Modifier
                                .width(16.dp)
                                .fillMaxHeight()
                                .padding(horizontal = 2.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = "➤",
                            color = Red,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                        Text(
                            text = "Workshops",
                            color = Color.Gray,
                            fontFamily = FontFamily(fonts = arrayOf(Font(R.font.hind_regular)))
                        )
                    }

                    Text(
                        text = "Yesterday, 9:16 PM",
                        color = Color.Gray, fontSize = 12.sp,
                        fontFamily = FontFamily(fonts = arrayOf(Font(R.font.hind_regular)))
                    )
                }

            }

            Text(
                text = "Learn some bollywood dance with the song \"Heaven's EP” performed by J-Cole",
                color = Color.DarkGray,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                fontFamily = FontFamily(fonts = arrayOf(Font(R.font.hind_regular)))
            )

            Spacer(modifier = Modifier.padding(top = 16.dp))
            VideoPlayer(exoPlayer = exoPlayer)
            Spacer(modifier = Modifier.padding(top = 16.dp))

            ReactionBar(liked, reacted)
        }
    }
}


@Composable
fun VideoPlayer(
    exoPlayer: SimpleExoPlayer
) {

    AndroidView({
        PlayerView(it).apply {

            val scale = context.resources.displayMetrics.density
            val pixels = (240 * scale + 0.5f).toInt()

            player = exoPlayer
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, pixels
            )

        }
    })
}
