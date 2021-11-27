package com.weskill2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.weskill2.R
import com.weskill2.ui.theme.Blue

@Composable
fun MemoriesLayout() {
    Column(
        modifier = Modifier
            .background(Color.LightGray.copy(0.2f))
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.background(Color.White)
            ) {
                Card(
                    modifier = Modifier
                        .width(195.dp)
                        .height(60.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray.copy(0.2f)),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = AnnotatedString("\uD83D\uDD25"),
                            fontSize = 22.sp,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(end = 8.dp)
                        )
                        Column {
                            Text(
                                text = "1.2k",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                text = "Total fires",
                                color = Color.LightGray,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(
                                    fonts = arrayOf(
                                        Font(R.font.poppins_regular)
                                    )
                                ),
                                fontWeight = FontWeight.Light
                            )
                        }
                    }

                }
                Card(
                    modifier = Modifier
                        .width(195.dp)
                        .height(60.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray.copy(0.2f)),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = AnnotatedString("❤"),
                            fontSize = 22.sp,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(end = 8.dp)
                        )
                        Column {
                            Text(
                                text = "1.2k",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                text = "Total likes",
                                color = Color.LightGray,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(
                                    fonts = arrayOf(
                                        Font(R.font.poppins_regular)
                                    )
                                ),
                                fontWeight = FontWeight.Light
                            )
                        }
                    }

                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(160.dp),
                RoundedCornerShape(10.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = "https://s1.1zoom.me/big3/63/Sky_athleticsLegs_479327.jpg"),
                    contentDescription = "Dislay Highlight",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            ImagePost(url = "https://images.freeimages.com/images/large-previews/84e/sunset-dance-1195920.jpg")

            ImagePost(url = "https://images.freeimages.com/images/large-previews/84e/sunset-dance-1195920.jpg")
        }

        Row(modifier = Modifier
            .padding(top = 25.dp, bottom = 25.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Go to my posts ",
                fontSize = 18.sp,
                fontFamily = FontFamily(
                    fonts = arrayOf(
                        Font(R.font.poppins_regular)
                    )
                ),
                fontWeight = FontWeight.Bold,
                color = Blue,
                textAlign = TextAlign.Center
            )
            Icon(painter = painterResource(id = R.drawable.ic_go_next),
                contentDescription = "Go to my post",
            tint = Blue)
        }
    }
}