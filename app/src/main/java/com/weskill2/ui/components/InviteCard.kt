package com.weskill2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weskill2.R
import com.weskill2.ui.theme.DarkGreen

@Composable
fun InviteCard(painter: Painter? = null, offer : String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
    ) {

        Card(modifier = Modifier
            .fillMaxSize(),
            RoundedCornerShape(10.dp),
            backgroundColor = DarkGreen
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_bg),
                contentDescription = "Refer background"
            )
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)) {
            Row(modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(35.dp),
                verticalAlignment = Alignment.Top) {

                Image(painter = painterResource(id = R.drawable.gift_box),
                    contentDescription = "Gift Box",
                    modifier = Modifier.size(60.dp, 80.dp),
                    contentScale = ContentScale.Fit)

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(text = offer,
                        fontSize = 14.sp,
                        color = Color.White)

                    Card(modifier = Modifier
                        .padding(vertical = 2.dp),
                        RoundedCornerShape(4.dp)
                    ) {
                        Text(text = AnnotatedString("Invite here"),
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 6.dp),
                            color = DarkGreen
                        )
                    }
                }
            }
        }
    }
}