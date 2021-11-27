package com.weskill2.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
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
import com.gowtham.ratingbar.RatingBar
import com.weskill2.R
import com.weskill2.helper.getActivity
import com.weskill2.ui.components.CertificatePost
import com.weskill2.ui.components.EditBackground
import com.weskill2.ui.components.InviteCard
import com.weskill2.ui.components.MemoriesLayout
import com.weskill2.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ProfileActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MainTheme() {
                Surface(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ShowBottomSheet()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun DrawProfileUi(
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_bg),
            contentDescription = "Display Profile Backgrund",
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .background(DarkGreen)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(0.2f))
        ) {

            Column(
                modifier = Modifier
                    .padding(top = 250.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
                {
                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 70.dp, bottom = 30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Cole Krishna",
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
                                painter = painterResource(id = R.drawable.ic_edit),
                                contentDescription = "Display edit Button",
                                modifier = Modifier
                                    .size(22.dp)
                                    .padding(start = 6.dp, bottom = 2.dp),
                                tint = Color.LightGray
                            )
                        }
                        Text(
                            text = "Member since August,2021",
                            textAlign = TextAlign.Center,
                            color = BlackLight.copy(0.8f),
                            fontSize = 14.sp,
                            fontFamily = FontFamily(
                                fonts = arrayOf(
                                    Font(R.font.poppins_regular)
                                )
                            ),
                            fontWeight = FontWeight.Light
                        )

                        Row(
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Certifications",
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
                                text = "SEE ALL(2)",
                                color = BlackLight.copy(0.8f),
                                fontSize = 16.sp,
                                fontFamily = FontFamily(
                                    fonts = arrayOf(
                                        Font(R.font.hind_regular)
                                    )
                                )
                            )
                        }

//                        CertificatePost(certificate)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(320.dp)
                        ) {
                            ShowCertificates()
                        }
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    val tabData = listOf(
                        "Memories",
                        "Timeline"
                    )

                    val pagerState = rememberPagerState()
                    val tabIndex = pagerState.currentPage

                    Column(modifier = Modifier.padding(top = 20.dp)) {
                        TabRow(
                            backgroundColor = Color.LightGray.copy(0.2f),
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
                                )
                                {}
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            tabData.forEachIndexed { index, text ->
                                Tab(
                                    selected = tabIndex == index,
                                    unselectedContentColor = Color.LightGray,
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
                                            fontFamily = FontFamily(
                                                fonts = arrayOf(
                                                    Font(R.font.poppins_regular)
                                                )
                                            )
                                        )
                                    },
                                    selectedContentColor = SelectedColour,
                                    modifier = Modifier.clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null
                                    ) {}

                                )
                            }
                        }

                        HorizontalPager(
                            state = pagerState,
                            count = tabData.size
                        ) { index ->
                            if (index == 0)
                                MemoriesLayout()
                            else
                                Text(text = tabData[index])
                        }
                    }
                }

                showTrophySection()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 30.dp, bottom = 30.dp, start = 20.dp, end = 20.dp)
                ) {
                    InviteCard(offer = "Get 100 skill coins for every 1 friend you invite to weskill!")
                }

                RatingSection(2.8f)

            }
        }


        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 40.dp)
                .height(200.dp),
            contentAlignment = Alignment.TopStart
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Display Back Button",
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(BlackLight.copy(alpha = 0.4f))
                                .padding(8.dp),
                            tint = Color.White
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = "Display Back Button",
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(BlackLight.copy(alpha = 0.4f))
                                .padding(10.dp),
                            tint = Color.White
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.ic_setting),
                            contentDescription = "Display Back Button",
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(BlackLight.copy(alpha = 0.4f))
                                .padding(8.dp),
                            tint = Color.White
                        )
                    }
                }


                Card(
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth()
                        .padding(start = 8.dp, bottom = 20.dp),
                    RoundedCornerShape(10.dp),
                    backgroundColor = BlackLight.copy(alpha = 0.4f)
                ) {
                    ClickableText(
                        text = AnnotatedString("Edit BG"),
                        style = TextStyle(color = Color.White),
                        modifier = Modifier
                            .padding(vertical = 6.dp, horizontal = 6.dp),
                        onClick = {

                            coroutineScope.launch {
                                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                    bottomSheetScaffoldState.bottomSheetState.expand()
                                } else {
                                    bottomSheetScaffoldState.bottomSheetState.collapse()
                                }
                            }
                        }
                    )

                }

            }
        }

    }
}


data class CertificateItem(
    val tag: String,
    val name: String,
    val participant_name: String,
    val url: String
)

val certificate = CertificateItem(
    "Dance",
    "Bollywood Dance",
    "Ashima Sharma",
    "https://static.vecteezy.com/system/resources/previews/000/285/508/original/vector-certificate.jpg"
)

val certificatesList = arrayListOf(certificate, certificate, certificate)


@Composable
fun ShowCertificates() {
    val listStata = rememberLazyListState()
    LazyRow(
        state = listStata,
        modifier = Modifier.fillMaxSize()
    ) {
        items(certificatesList.count()) { i ->
            CertificatePost(certificate = certificatesList[i])
        }
    }
}

@Composable
fun showTrophySection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 30.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Trophy Cases",
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
                    text = "SEE ALL",
                    color = BlackLight.copy(0.8f),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        fonts = arrayOf(
                            Font(R.font.hind_regular)
                        )
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Trophy(
                    "https://cdn.schoolstickers.com/products/en/819/139950-00.png",
                    "Completed First Course"
                )
                Trophy(title = "Dancer Mancer")
                Trophy(title = "Singing")
                Trophy(title = "Art Hero")
            }

        }

    }
}

@Composable
fun Trophy(url: Any? = null, title: String) {
    Column(
        modifier = Modifier
            .width(85.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = if (url != null) rememberImagePainter(data = url) else painterResource(id = R.drawable.ic_lock),
            contentDescription = "Display Trophy",
            modifier = Modifier
                .size(80.dp, 80.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(0.2f)),
            contentScale = ContentScale.Inside
        )

        Text(
            text = title,
            color = BlackLight.copy(0.8f),
            fontSize = 12.sp,
            fontFamily = FontFamily(
                fonts = arrayOf(
                    Font(R.font.poppins_regular)
                )
            ),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun RatingSection(rating: Float) {
    var rating by remember { mutableStateOf(rating) }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(30.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Rate us",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontFamily(
                    fonts = arrayOf(
                        Font(R.font.poppins_regular)
                    )
                ),
                fontWeight = FontWeight.ExtraBold
            )

            RatingBar(
                value = rating,
                size = 50.dp,
                padding = 4.dp,
                inactiveColor = Color.LightGray,
                onValueChange = {
                    rating = it
                }
            ) {
//                Toast.makeText(context, "Thank You", Toast.LENGTH_SHORT).show()
            }

            Text(
                text = "\uD83D\uDE01 Very good, Nicee!",
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = FontFamily(
                    fonts = arrayOf(
                        Font(R.font.hind_regular)
                    )
                ),
                fontWeight = FontWeight.ExtraBold
            )

            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 30.dp)
                    .wrapContentWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Rate us on the app store ",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(
                        fonts = arrayOf(
                            Font(R.font.poppins_regular)
                        )
                    ),
                    fontWeight = FontWeight.Bold,
                    color = Blue,
                    textAlign = TextAlign.Center
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_go_next),
                    contentDescription = "Rate us on store",
                    tint = Blue
                )
            }
        }
    }
}

//lateinit var coroutineScope : CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowBottomSheet() {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val selectedColor = remember {
        mutableStateOf(0)
    }
    val selectedBg = remember {
        mutableStateOf(2)
    }

//    coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            EditBackground(selectedColor = selectedColor, selectedBg = selectedBg)
        },
        modifier = Modifier
            .fillMaxSize(),
        sheetPeekHeight = 0.dp
    ) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(1) {
                DrawProfileUi(
                    bottomSheetScaffoldState
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultProfilePrev() {
    WeSkillTheme {
        ShowBottomSheet()
    }
}