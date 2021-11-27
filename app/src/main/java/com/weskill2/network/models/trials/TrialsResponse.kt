package com.weskill2.network.models.trials

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.weskill2.network.models.media.MediaResponse

data class TrialsResponse(
    @SerializedName("AgeGroup") val ageGroup: String,
    @SerializedName("trials_bookings") val trialBookings: ArrayList<Any>,
    @SerializedName("corse") val course: Course
)

data class Course(
    @SerializedName("category") val category: String,
    @SerializedName("image") val image: MediaResponse
)
