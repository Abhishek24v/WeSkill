package com.weskill2.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.weskill2.R
import com.weskill2.network.models.Resource
import com.weskill2.network.models.likes_and_comments.CommentsResponse
import com.weskill2.ui.components.*
import com.weskill2.ui.theme.LightGray
import com.weskill2.ui.theme.WeSkillTheme
import com.weskill2.viewmodels.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PostActivity : ComponentActivity() {

    companion object{

        private var tweetItem: TweetItem?=null
         fun startActivity(context: Context, _tweet: TweetItem) {
             context.startActivity(Intent(context,PostActivity::class.java))
             tweetItem = _tweet
         }

    }

    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeSkillTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LightGray
                ) {
                    DrawPostActivityUI(tweetItem, viewModel)
                }
            }
        }
    }
}

@Composable
fun DrawPostActivityUI(item: TweetItem?, viewModel: PostViewModel) {
    if (item==null)
        return

    val commentList = remember { mutableStateOf(ArrayList<CommentsResponse>())}
    val loadingComments = remember { mutableStateOf(true) }

    viewModel.getComments("607b3b90fd3ea2001589f52b").observe(LocalLifecycleOwner.current) {
        when(it) {
            is Resource.Success -> {
                loadingComments.value = false
                commentList.value = it.r
            }
            is Resource.Error -> {
            }
        }
    }

    var exoPlayer: SimpleExoPlayer?=null
    if (item.mime == "video/mp4")
        exoPlayer=getExoplayer(url = item.url)

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {

            item {
                if (item.mime == "video/mp4") {
                    VideoPost(exoPlayer = exoPlayer!!)
                } else {
                    ImagePost(url = item.url)
                }
            }
            item {
                if (loadingComments.value)
                    CircularProgressIndicator(
                        color = Color.LightGray,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
            }

            item {
                if (!loadingComments.value) {
                    val n =
                        if (commentList.value.isEmpty()) "No" else commentList.value.size.toString()
                    Text(
                        text = "$n comments",
                        modifier = Modifier.padding(
                            start = 24.dp,
                            top = 12.dp,
                            end = 24.dp,
                            bottom = 12.dp
                        ),
                        fontSize = 18.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.hind_regular))
                    )
                }
            }

            item {
                if (!loadingComments.value && commentList.value.isNullOrEmpty()) {
                    Text(
                        text = "Be the first one to comment!",
                        modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
                        fontSize = 18.sp,
                        color = Color.Gray,
                        fontFamily = FontFamily(Font(R.font.hind_regular))
                    )
                }
            }
            items(commentList.value.filter { it.owner != null && it.approved }) { i ->
                val reacted = remember { mutableStateOf(false) }
                CommentUI(i = i, reacted = reacted)
                Spacer(modifier = Modifier.padding(top = 1.dp))
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
            }
        }


    }

}

@Composable
fun getExoplayer(url:String) : SimpleExoPlayer {
    val context = LocalContext.current

    val exoPlayer = remember(context) {
        SimpleExoPlayer.Builder(context).build().apply {
            repeatMode = Player.REPEAT_MODE_ALL

            val dataSourceFactory = DefaultDataSourceFactory(
                context,
                Util.getUserAgent(context, context.packageName)
            )

            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse(url)))

            setMediaSource(source)
            Timber.d("Preparing")
            prepare()
            playWhenReady=true

        }
    }

    val lifecycleOwner by rememberUpdatedState(LocalLifecycleOwner.current)
    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    exoPlayer.playWhenReady=true
                }
                Lifecycle.Event.ON_PAUSE -> {
                    exoPlayer.playWhenReady=false
                }
                Lifecycle.Event.ON_DESTROY -> {
                    exoPlayer.run {
                        playWhenReady=false
                        stop()
                        release()
                    }
                }
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
    return exoPlayer

}