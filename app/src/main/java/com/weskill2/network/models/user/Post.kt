package com.weskill2.network.models.user

data class Post(
    val __v: Int,
    val _id: String,
    val caption: String,
    val content: List<Content>,
    val createdAt: String,
    val groups: List<String>,
    val id: String,
    val learner_profile: Any,
    val owner: String,
    val published_at: String,
    val updatedAt: String
)