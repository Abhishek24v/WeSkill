package com.weskill2.network.models.likes_and_comments

import com.google.gson.annotations.SerializedName
import com.weskill2.network.models.media.MediaResponse

data class CommentsResponse(
    @SerializedName("approved") val approved: Boolean,
    @SerializedName("id") val id: String,
    @SerializedName("comment") val comment: String,
    @SerializedName("published_at") val ts: String,
    @SerializedName("user") val owner: Owner
)

data class Owner(
    @SerializedName("name") val name: String,
    @SerializedName("ProfilePhoto") val image: MediaResponse
)