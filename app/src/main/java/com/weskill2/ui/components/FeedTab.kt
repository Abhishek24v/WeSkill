package com.weskill2.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.MutableLiveData
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.weskill2.R
import com.weskill2.helper.getActivity
import com.weskill2.ui.activities.PostActivity
import com.weskill2.ui.theme.Red
import timber.log.Timber
import kotlin.math.abs


@Composable
fun ReactionBar(liked: MutableState<Boolean>, reacted: MutableState<Boolean>) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            ClickableText(
                text = AnnotatedString("\uD83D\uDD25"),
                onClick = { liked.value = !liked.value },
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .border(
                        2.dp,
                        if (!liked.value) Color.LightGray.copy(alpha = 0.6f) else Red,
                        RoundedCornerShape(14.dp)
                    )
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        if (!liked.value) Color.LightGray.copy(alpha = 0.2f) else Red.copy(
                            alpha = 0.2f
                        )
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
            ClickableText(
                text = AnnotatedString("❤️ 12"), onClick = { reacted.value = !reacted.value },
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 4.dp)
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
        Row {
            Text(
                text = "2.2K",
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .fillMaxHeight(),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(20.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_share),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(20.dp)
            )
        }
    }

}

//val hashMap = HashMap<Int, SimpleExoPlayer?>()
val image = Tweet(
    "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
    "video/mp4"
)
val video = Tweet(
    "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "video/mp4"
)
val postList = arrayListOf(image, image, image, video, video, video, video, video, video)

@Composable
fun TweetList(selectedTab: MutableState<Int>, thisTab: Int) {

    val state = ArrayList<TweetItem>()
    postList.forEachIndexed { i, t ->
        state.add(TweetItem(t.url, t.mime, i))
    }

    val listState = rememberLazyListState()

    currentMiddleItem.value =
        determineCurrentlyPlayingItem(listState, state)

    UpdateCurrentlyPlayingItem(currentMiddleItem.value)

    LazyColumn(state = listState) {
        items(state.count()) { i ->
            TweetItem(state[i], selectedTab, thisTab)
        }
    }
}

data class TweetItem(
    val url: String,
    val mime: String,
    val id: Int
)

data class Tweet(
    val url: String,
    val mime: String
)

//class TweetItems = ArrayList<TweetItem>()

@Composable
private fun TweetItem(tweetItem: TweetItem, selectedTab: MutableState<Int>, thisTab: Int) {

    val context = LocalContext.current
    if (tweetItem.mime == "image/jpg") {
        ImagePost(url = tweetItem.url, onClick = {
            PostActivity.startActivity(context = context, _tweet = tweetItem)
        })
        return
    }

    val id = tweetItem.id

    val isPlaying = remember { mutableStateOf(true) }
    val middle = currentMiddleItem.observeAsState()
    isPlaying.value = middle.value?.id == id && selectedTab.value==thisTab

    val exoPlayer = getExoPlayer(isPlaying = isPlaying, tweetItem = tweetItem)

    if (isPlaying.value) {
        exoPlayer.playWhenReady = true
        VideoPost(exoPlayer = exoPlayer, onClick = {
            PostActivity.startActivity(context = context, _tweet = tweetItem)
        })
    } else {
        exoPlayer.playWhenReady = false
        ThumbnailPost(url = tweetItem.url, onClick = {
            PostActivity.startActivity(context = context, _tweet = tweetItem)
        })
    }

}

@Composable
fun getExoPlayer(isPlaying: MutableState<Boolean>, tweetItem: TweetItem): SimpleExoPlayer {
    val context = LocalContext.current

    val exoPlayer = remember(context) {
        SimpleExoPlayer.Builder(context).build().apply {
            repeatMode = Player.REPEAT_MODE_ALL

            val dataSourceFactory = DefaultDataSourceFactory(
                context,
                Util.getUserAgent(context, context.packageName)
            )

            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse(tweetItem.url)))

            setMediaSource(source)
            prepare()

        }
    }

    val lifecycleOwner by rememberUpdatedState(LocalLifecycleOwner.current)
    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    exoPlayer.playWhenReady = false
                    Timber.d("Pause")
                }
                Lifecycle.Event.ON_RESUME -> {
                    exoPlayer.playWhenReady = isPlaying.value
                    Timber.d("Resumed")
                }
                Lifecycle.Event.ON_DESTROY -> {
                    exoPlayer.run {
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

private val currentMiddleItem = MutableLiveData<TweetItem?>()

private fun determineCurrentlyPlayingItem(listState: LazyListState, items: ArrayList<TweetItem>): TweetItem? {
    val layoutInfo = listState.layoutInfo
    val visibleTweets = layoutInfo.visibleItemsInfo.map { items[it.index] }
    return if (visibleTweets.size == 1) {
        visibleTweets.first()
    } else {
        val midPoint = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
        val itemsFromCenter =
            layoutInfo.visibleItemsInfo.sortedBy { abs((it.offset + it.size / 2) - midPoint) }
        itemsFromCenter.map { items[it.index] }.firstOrNull { it.mime == "video/mp4" }
    }
}

@Composable
private fun UpdateCurrentlyPlayingItem(tweet: TweetItem?) {
    currentMiddleItem.value = tweet
}