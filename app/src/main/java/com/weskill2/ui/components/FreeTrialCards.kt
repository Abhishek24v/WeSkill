package com.weskill2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.math.MathUtils.lerp
import com.weskill2.helper.makeWord
import com.weskill2.network.models.trials.TrialsResponse
import com.weskill2.ui.theme.Orange
import kotlin.math.absoluteValue

@OptIn(ExperimentalCoilApi::class, com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun FreeTrialCards(list: ArrayList<TrialsResponse>) {

    HorizontalPager(
        count = if (list.isNullOrEmpty()) 1 else list.size,
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(bottom = 30.dp)
    ) { pos ->
        Card(
            Modifier
                .fillMaxSize(0.9f)
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(pos).absoluteValue
                    lerp(
                        0.85f,
                        1f,
                        1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        0.5f,
                        1f,
                        1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .shadow(elevation = 12.dp, shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
        ) {
            if (list.size == 0) {
                Box(
                    modifier = Modifier
                        .height(240.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .background(Color.LightGray)
                            .fillMaxSize(), text = ""
                    )
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                }
            } else Box(
                modifier = Modifier
                    .height(240.dp)
                    .fillMaxWidth()
            ) {
                var booking = list[pos].trialBookings?.size ?: 30
                if (booking == 0)
                    booking = 10

                Image(
                    painter = rememberImagePainter(list[pos].course.image.url),
                    contentDescription = "Course image",
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "$booking+ enrolled",
                    color = Color.White,
                    modifier = Modifier
                        .padding(12.dp)
                        .border(1.dp, Color.Transparent)
                        .background(color = Color(0xB3060606), shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    fontSize = 12.sp
                )
                Text(
                    text = if (list[pos].ageGroup.isNullOrBlank()) "8-12 yrs" else list[pos].ageGroup,
                    color = Color.White,
                    modifier = Modifier
                        .padding(12.dp)
                        .border(1.dp, Color.Transparent)
                        .background(color = Color(0xB3060606), shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                        .align(Alignment.BottomStart),
                    fontSize = 12.sp
                )
                Text(
                    text = list[pos].course.category.makeWord(),
                    color = Color.White,
                    modifier = Modifier
                        .padding(12.dp)
                        .border(1.dp, Color.Transparent)
                        .background(color = Orange, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                        .align(Alignment.TopEnd),
                    fontSize = 12.sp
                )
            }
        }
    }

}