package com.weskill2.helper

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.*

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun String.makeWord(): String {
    val word = this.toCharArray()
    word[0] = word[0].uppercaseChar()
    return String(word)
}

fun String.getThumbnail(): String {
    if (this.length <= 3)
        return this
    val word = this.toCharArray()
    word[word.size - 1] = 'g'
    word[word.size - 2] = 'p'
    word[word.size - 3] = 'j'
    return String(word)
}

fun String.toDate(
    dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): Date? {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String="dd MMMM', 'hh:mm a", timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}