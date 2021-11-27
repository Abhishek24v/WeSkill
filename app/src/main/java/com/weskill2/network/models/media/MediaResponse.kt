package com.weskill2.network.models.media

import com.google.gson.annotations.SerializedName

data class MediaResponse(
    @SerializedName("id") val id: String,
    @SerializedName("mime") val mime: String,
    @SerializedName("url") val url: String
)
