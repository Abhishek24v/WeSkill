package com.weskill2.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.weskill2.R
import com.weskill2.helper.getActivity
import com.weskill2.ui.theme.BlueDark
import com.weskill2.ui.theme.BlueLight
import com.weskill2.ui.theme.SplashTheme
import com.weskill2.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SplashTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.DarkGray
                ) {
                    DrawUI(viewModel)
                }
            }
        }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DrawUI(viewModel: MainViewModel? = null) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(Brush.verticalGradient(listOf(BlueLight, BlueDark)))
    ) {

        Image(
            painter = painterResource(id = R.drawable.image_splash),
            contentDescription = "Splash Image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.image_text_weskill),
            contentDescription = "Splash Text",
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(vertical = 100.dp),
            contentScale = ContentScale.FillWidth
        )
    }

    val activity = LocalContext.current.getActivity()


    Handler(Looper.getMainLooper()).postDelayed({

        val loggedIn = viewModel?.isLoggedIn() ?: false

        activity?.let {
            it.startActivity(
                Intent(
                    it.baseContext,
                    if (loggedIn) LoggedInActivity::class.java else LoggedInActivity::class.java
                )
            )
        }
        activity?.finish()
    }, 2000)

}