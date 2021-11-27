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
import androidx.compose.runtime.MutableState
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
import coil.compose.rememberImagePainter
import com.weskill2.R
import com.weskill2.helper.formatTo
import com.weskill2.helper.toDate
import com.weskill2.network.models.likes_and_comments.CommentsResponse
import com.weskill2.ui.theme.Blue
import com.weskill2.ui.theme.Red

@Composable
fun CommentUI(i: CommentsResponse, reacted: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 80.dp)) {

            Text(
                text = i.owner.name,
                modifier = Modifier.padding(top = 24.dp),
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.hind_regular))
            )
            Text(
                text = i.ts.toDate()?.formatTo() ?: "",
                modifier = Modifier.padding(top = 4.dp),
                fontSize = 12.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily(Font(R.font.hind_regular))
            )
            Text(
                text = i.comment,
                modifier = Modifier.padding(top = 8.dp),
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.hind_regular))
            )
            ClickableText(
                text = AnnotatedString(
                    "❤️ 12", spanStyle = SpanStyle(
                        color = Color.Black,
                        fontSize = 12.sp
                    ),
                    paragraphStyle = ParagraphStyle(textAlign = TextAlign.Center)
                ), onClick = { reacted.value = !reacted.value },
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 12.dp)
                    .border(
                        2.dp,
                        if (!reacted.value) Color.LightGray.copy(alpha = 0.6f) else Red,
                        RoundedCornerShape(14.dp)
                    )
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        if (!reacted.value) Color.LightGray.copy(alpha = 0.2f)
                        else Red.copy(alpha = 0.2f)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        Image(
            painter = rememberImagePainter(i.owner?.image.url),
            contentDescription = "owner dp",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .border(24.dp, Color.Transparent)
                .padding(24.dp)
                .size(36.dp)
                .background(Color.LightGray, CircleShape)
                .clip(CircleShape)
        )
        
        Image(
            painter = painterResource(id = R.drawable.ic_options),
            contentDescription = "owner dp",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .border(24.dp, Color.Transparent)
                .padding(24.dp)
                .size(36.dp)
                .background(Color.LightGray, CircleShape)
                .clip(CircleShape)
                .align(Alignment.TopEnd)
        )
    }
}