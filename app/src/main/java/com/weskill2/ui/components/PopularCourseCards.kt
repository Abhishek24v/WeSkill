package com.weskill2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.math.MathUtils
import com.weskill2.R
import com.weskill2.ui.theme.BlackLight
import com.weskill2.ui.theme.Green
import com.weskill2.ui.theme.Orange
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class, coil.annotation.ExperimentalCoilApi::class)
@Composable
fun PopularCourseCards(list: ArrayList<Any>, darkMode: Boolean = isSystemInDarkTheme()) {

    HorizontalPager(
        count = if (list.isNullOrEmpty()) 1 else list.size,
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(bottom = 30.dp)
    ) { pos ->
        Card(
            modifier =
            Modifier
                .fillMaxSize(0.9f)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(pos).absoluteValue
                    MathUtils
                        .lerp(
                            0.85f,
                            1f,
                            1f - pageOffset.coerceIn(0f, 1f)
                        )
                        .also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    alpha = MathUtils.lerp(
                        0.5f,
                        1f,
                        1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
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
            } else Row(
                modifier = Modifier
                    .height(240.dp)
                    .fillMaxWidth()
                    .background(color = if (darkMode) BlackLight else Color.White),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color.DarkGray)
                        .fillMaxWidth(0.45f),
                    contentDescription = "Instructor Image",
                    painter = rememberImagePainter("https://res.cloudinary.com/ddjrxbi2d/image/upload/v1631526836/Bhumika_7f67fa3d68.webp"),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Dance",
                            color = Orange,
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Orange,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                                .clip(shape = RoundedCornerShape(8.dp)),
                            fontSize = 12.sp
                        )

                        Text(
                            text = "4.4⭐",
                            color = Color.White,
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(Green)
                                .padding(horizontal = 8.dp, vertical = 2.dp),
                            fontSize = 12.sp
                        )
                    }
                    Text(
                        text = "Bollywood Dance",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(fonts = arrayOf(Font(R.font.poppins_regular))),
                        fontWeight = FontWeight.ExtraBold,
                        color = if (darkMode) Color.White else Color.Black,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Text(
                        text = "Amisha Verma",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(fonts = arrayOf(Font(R.font.hind_regular))),
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sessions),
                            contentDescription = "session icon",
                            modifier = Modifier
                                .size(16.dp)
                                .padding(end = 2.dp),
                            tint = Color.Gray
                        )
                        Text(
                            text = "8 sessions",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(fonts = arrayOf(Font(R.font.hind_regular))),
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun PopularCourseCardsPreview() {
    PopularCourseCards(arrayListOf(object : Any() {}), true)
}