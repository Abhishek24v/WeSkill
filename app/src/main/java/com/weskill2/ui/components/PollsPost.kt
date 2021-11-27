package com.weskill2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.layout.FlowColumn
import coil.compose.rememberImagePainter
import com.weskill2.R
import com.weskill2.ui.theme.Blue
import com.weskill2.ui.theme.Red


@Composable
fun PollsPost() {

    val liked = remember { mutableStateOf(false) }
    val reacted = remember { mutableStateOf(false) }
    val voted = remember { mutableStateOf(false) }
    var selectedOption = -1
    val listOfOptions =
        arrayListOf("J-cole - Heaven's EP", "Forest Hills Drive", "Other (add as comment)")

    Box(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth()
            .background(color = Color.White)
    ) {

//        FlowColumn {
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .padding(start = 20.dp, top = 20.dp)
//            ) {
//
//                Image(
//                    painter = rememberImagePainter("https://weskill.co.in/static/media/weskill_element_final.8df0e71d.png"),
//                    contentDescription = "owner dp",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(36.dp)
//                        .clip(CircleShape)
//                        .background(Color.LightGray)
//                )
//                Column(modifier = Modifier.padding(start = 16.dp)) {
//
//                    Row {
//                        Text(
//                            text = "WeSkill",
//                            color = Color.Black,
//                            fontSize = 18.sp,
//                            fontFamily = FontFamily(fonts = arrayOf(Font(R.font.hind_regular)))
//                        )
//                        Image(
//                            painter = painterResource(id = R.drawable.ic_verified),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .width(16.dp)
//                                .fillMaxHeight()
//                                .padding(horizontal = 2.dp)
//                                .align(Alignment.CenterVertically)
//                        )
//                        Text(
//                            text = "➤",
//                            color = Red,
//                            modifier = Modifier.padding(horizontal = 4.dp)
//                        )
//                        Text(
//                            text = "Workshops",
//                            color = Color.Gray,
//                            fontFamily = FontFamily(fonts = arrayOf(Font(R.font.hind_regular)))
//                        )
//                    }
//
//                    Text(
//                        text = "Yesterday, 9:16 PM",
//                        color = Color.Gray, fontSize = 12.sp,
//                        fontFamily = FontFamily(fonts = arrayOf(Font(R.font.hind_regular)))
//                    )
//                }
//
//            }
//
//            Text(
//                text = "Learn some bollywood dance with the song \"Heaven's EP” performed by J-Cole",
//                color = Color.DarkGray,
//                fontSize = 14.sp,
//                modifier = Modifier
//                    .padding(horizontal = 16.dp)
//                    .padding(top = 16.dp),
//                fontFamily = FontFamily(fonts = arrayOf(Font(R.font.hind_regular)))
//            )
////            Image(
////                painter = rememberImagePainter("https://res.cloudinary.com/ddjrxbi2d/image/upload/v1618778088/Untitled_design_1_5354666002.png"),
////                contentDescription = null,
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .height(240.dp)
////                    .padding(vertical = 16.dp)
////                    .background(color = Color.LightGray),
////                contentScale = ContentScale.Crop
////            )
//            listOfOptions.forEachIndexed { i, text ->
//                ClickableText(
//                    text = AnnotatedString(
//                        text = text, SpanStyle(
//                            color = if (voted.value && selectedOption == i) Blue else Color.DarkGray,
//                            fontFamily = FontFamily(fonts = arrayOf(Font(R.font.hind_regular))),
//                            fontSize = 14.sp,
//                            fontWeight = FontWeight.Bold
//                        ),
//                        paragraphStyle = ParagraphStyle(textAlign = TextAlign.Center)
//                    ),
//                    onClick = {
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth(0.8f)
//                        .background(Color.White)
//                        .border(width = 12.dp, color = Color.Transparent)
//                        .padding(vertical = 4.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                        .background(color = Color(0xFFF9FAFF))
//                        .border(width = 2.dp, color = Blue, shape = RoundedCornerShape(8.dp))
//                        .padding(vertical = 8.dp)
//                        .align(Alignment.Center)
//                )
//            }
//        }

        ReactionBar(liked, reacted)
    }
}
