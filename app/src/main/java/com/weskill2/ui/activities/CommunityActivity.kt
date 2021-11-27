package com.weskill2.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.weskill2.R
import com.weskill2.helper.getActivity
import com.weskill2.ui.components.*
import com.weskill2.ui.theme.WeSkillTheme
import com.weskill2.ui.theme.Blue
import com.weskill2.ui.theme.MainTheme
import com.weskill2.ui.theme.SelectedColour
import kotlinx.coroutines.launch

class CommunityActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MainTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DrawCommunityUI()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DrawCommunityUI() {
    Column(modifier = Modifier.background(color = Color.White)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 34.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val activity = LocalContext.current.getActivity()
            Image(
                painter = rememberImagePainter("https://res.cloudinary.com/ddjrxbi2d/image/upload/v1618593765/Whats_App_Image_2021_04_14_at_10_40_47_AM_47e823cb14.jpg"),
                contentDescription = "Display picture",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(color = Color.Black)
                    .clickable(true,
                    onClick = {
                        activity?.let {
                            it.startActivity(
                                Intent(
                                    it.baseContext,
                                    ProfileActivity::class.java
                                )
                            )
                        }
                    }),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Community",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    fonts = arrayOf(
                        Font(R.font.poppins_regular)
                    )
                ),
                fontWeight = FontWeight.ExtraBold
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_options),
                contentDescription = "Options",
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape),
                tint = Color.LightGray
            )
        }

        CommunityTab()

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CommunityTab() {
    val tabData = listOf(
        "FEED",
        "STAGE",
        "CONTEST"
    )

    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column {
        TabRow(
            backgroundColor = Color.White,
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Divider(
                    modifier = Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxHeight(), color = SelectedColour
                )
            },
            modifier = Modifier
                .clickable(

                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {}
        ) {
            tabData.forEachIndexed { index, text ->
                Tab(
                    selected = tabIndex == index,
                    unselectedContentColor = Color.White,
                    selectedContentColor = SelectedColour,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                    text = {
                        val selected = tabIndex == index
                        Text(
                            text = text,
                            color = if (!selected) Color.LightGray else Blue,
                            textAlign = TextAlign.Center,
                        )
                    },
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {}

                )
            }
        }
        val context = LocalContext.current
        val selectedTab= remember { mutableStateOf(0) }
        selectedTab.value = pagerState.currentPage

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
            count = tabData.size
        ) { index ->
            if (index == 0)
                TweetList(selectedTab,0)
            else if (index==1){

                Box(modifier = Modifier.fillMaxSize()) {
                    TweetList(selectedTab = selectedTab, thisTab = 1)

                }

            } else
                Text(text = tabData[index])
        }
    }
}
