package com.weskill2.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weskill2.R
import com.weskill2.ui.theme.Blue


@Composable
fun TrialPost(trialImage: String) {

    val onClick = {

    }
    ImagePost(onClick = onClick, url = trialImage)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 16.dp)
    ) {

        ClickableText(
            text = AnnotatedString(
                text = "Register Here!", SpanStyle(
                    color = Blue,
                    fontFamily = FontFamily(fonts = arrayOf(Font(R.font.poppins_regular))),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                paragraphStyle = ParagraphStyle(textAlign = TextAlign.Center)
            ),
            onClick = { onClick() },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(Color.White)
                .border(width = 12.dp, color = Color.Transparent)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color(0xFFF9FAFF))
                .border(width = 2.dp, color = Blue, shape = RoundedCornerShape(8.dp))
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
