package com.weskill2.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.pager.ExperimentalPagerApi
import com.weskill2.R
import com.weskill2.helper.getActivity
import com.weskill2.network.models.Resource
import com.weskill2.network.models.trials.TrialsResponse
import com.weskill2.ui.components.AppHeader
import com.weskill2.ui.components.FreeTrialCards
import com.weskill2.ui.components.PopularCourseCards
import com.weskill2.ui.theme.MainTheme
import com.weskill2.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MainTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DrawHomeUI(viewModel = viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class, coil.annotation.ExperimentalCoilApi::class)
@Composable
fun DrawHomeUI(viewModel: MainViewModel) {

    val trialResult = remember { mutableStateOf(ArrayList<TrialsResponse>()) }

    viewModel.getTrials().observe(LocalContext.current.getActivity() as LifecycleOwner) {
        when (it) {
            is Resource.Success -> {
                Timber.d("Success")
                trialResult.value = it.r
            }
            is Resource.Error -> {
            }
        }
    }

    LazyColumn {

        item {
            AppHeader("Hello!")
        }

        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Free Trials",
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )

                Text(
                    text = "See All",
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
                    fontSize = 18.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.hind_regular))
                )
            }


        }

        item {
            FreeTrialCards(trialResult.value)
        }

        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Popular Courses⚡",
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )

                Text(
                    text = "See All",
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
                    fontSize = 18.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.hind_regular))
                )
            }

        }

        item {
            PopularCourseCards(list = arrayListOf(
                object : Any() {},
                object : Any() {},
                object : Any() {},
                object : Any() {},
                object : Any() {}
            ))
        }

        item {
            val context = LocalContext.current
            Button(modifier = Modifier.fillMaxWidth().padding(10.dp), onClick = {
                context.startActivity(Intent(context,CommunityActivity::class.java))
            }) {
                Text(text = "Community",modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }

    }
}