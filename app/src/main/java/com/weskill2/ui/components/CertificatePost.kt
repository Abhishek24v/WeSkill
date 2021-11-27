package com.weskill2.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.weskill2.R
import com.weskill2.ui.activities.CertificateItem
import com.weskill2.ui.theme.BlackLight
import com.weskill2.ui.theme.Green
import com.weskill2.ui.theme.Red

@OptIn(ExperimentalCoilApi::class, ExperimentalFoundationApi::class)
@Composable
fun CertificatePost(certificate : CertificateItem){
    Card(
        modifier = Modifier
            .padding(top = 10.dp, end = 10 .dp)
            .height(320.dp)
            .width(350.dp),
        RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray.copy(0.2f))
                .padding(14.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp)
            ) {
                Text(
                    text = certificate.tag,
                    modifier = Modifier
                        .border(
                            2.dp,
                            Red,
                            RoundedCornerShape(14.dp)
                        )
                        .clip(RoundedCornerShape(14.dp))
                        .padding(vertical = 4.dp, horizontal = 6.dp),
                    color = Red,
                    fontSize = 14.sp
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = "Display Share Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.LightGray
                )
            }

            Text(
                text = certificate.name,
                textAlign = TextAlign.Start,
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontFamily(
                    fonts = arrayOf(
                        Font(R.font.poppins_regular)
                    )
                ),
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = certificate.participant_name,
                textAlign = TextAlign.Start,
                color = BlackLight.copy(0.8f),
                fontSize = 14.sp,
                fontFamily = FontFamily(
                    fonts = arrayOf(
                        Font(R.font.poppins_regular)
                    )
                ),
                fontWeight = FontWeight.Light
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .height(200.dp),
                RoundedCornerShape(10.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Green.copy(0.2f),
                                Green.copy(0.8f)
                            )
                        )
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {

                    Card(
                        modifier = Modifier
                            .height(160.dp)
                            .width(200.dp),
                        RoundedCornerShape(10.dp),

                        ) {
                        Image(painter = rememberImagePainter(certificate.url),
                            contentDescription = "Display Certificate",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop)
                    }
                }
            }
        }
    }
}

