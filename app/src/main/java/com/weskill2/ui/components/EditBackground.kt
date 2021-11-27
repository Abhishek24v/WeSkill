package com.weskill2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weskill2.R
import com.weskill2.ui.theme.*

@Composable
fun EditBackground(selectedColor: MutableState<Int>, selectedBg: MutableState<Int>) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Color",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontFamily(
                    fonts = arrayOf(
                        Font(R.font.poppins_regular)
                    )
                ),
                fontWeight = FontWeight.Bold
            )
            Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                for (i in 0..3) {
                    if (selectedColor.value == i) {
                        ColorPallet(index = i, selectedColor)
                    } else {
                        ColorPallet(index = i, selectedColor)
                    }
                }
            }
            Text(
                text = "Background",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontFamily(
                    fonts = arrayOf(
                        Font(R.font.poppins_regular)
                    )
                ),
                fontWeight = FontWeight.Bold
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                for (i in 0..3) {
                    if (selectedBg.value == i) {
                        BackgroundPallet(index = i, selectedBg)
                    } else {
                        BackgroundPallet(index = i, selectedBg = selectedBg)
                    }
                }
            }
        }
    }

}

@Composable
fun ColorPallet(index: Int, selectedColor: MutableState<Int>) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .width(30.dp)
            .height(30.dp)
            .background(
                when (index) {
                    0 -> Blue
                    1 -> Green
                    2 -> LightYellow
                    else -> LightRed
                }
            )
            .clickable {
                if (selectedColor.value != index) {
                    selectedColor.value = index
                }
            }

    ) {
        if (selectedColor.value == index) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "Show Checked",
                modifier = Modifier.padding(4.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
fun BackgroundPallet(index: Int, selectedBg: MutableState<Int>) {
    Box(
        modifier = Modifier
            .width(70.dp)
            .height(70.dp)

    ) {
        Image(
            painter = painterResource(
                id = when (index) {
                    0 -> R.drawable.profile_bg
                    1 -> R.drawable.profile_bg
                    2 -> R.drawable.profile_bg
                    else -> R.drawable.profile_bg
                }
            ),
            contentDescription = "Show Bg",
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize()
                .background(DarkGreen)
                .clickable {
                    if (selectedBg.value != index) {
                        selectedBg.value = index
                    }
                }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 2.dp),
            contentAlignment = Alignment.TopEnd
        ) {

            if (selectedBg.value == index) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "Show Checked",
                    modifier = Modifier
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(1.dp)
                        .clip(CircleShape)
                        .background(DarkGreen)
                        .padding(1.dp),
                    tint = Color.White
                )
            }
        }
    }
}