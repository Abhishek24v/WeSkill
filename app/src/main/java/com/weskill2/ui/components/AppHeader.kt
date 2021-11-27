package com.weskill2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weskill2.R

@Composable
fun AppHeader(name: String, darkMode: Boolean = isSystemInDarkTheme()) {
    Card(modifier = Modifier.height(260.dp)) {

        Image(
            painter = painterResource(id = if (darkMode) R.drawable.header_bg_dark else R.drawable.header_bg),
            contentDescription = "Header background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(RoundedCornerShape(0.dp)).fillMaxWidth()
        )
        Column(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 48.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user_image),
                    modifier = Modifier
                        .border(width = 4.dp, color = Color(0xFF576AD8), shape = CircleShape)
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    contentDescription = "User image"
                )
                Column(modifier = Modifier.align(CenterVertically)) {
                    Text(
                        text = name,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(fonts = arrayOf(Font(R.font.poppins_regular))),
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "Edit button",
                    modifier = Modifier
                        .size(24.dp)
                        .align(CenterVertically)
                        .padding(bottom = 4.dp),
                    tint = Color.White
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_mascot),
                contentDescription = "Mascot",
                modifier = Modifier
                    .width(260.dp)
                    .align(End),
                contentScale = ContentScale.FillWidth
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderPreview() {
    AppHeader(name = "Hello!")
}