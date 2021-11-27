package com.weskill2.network.models.user

data class Group(
    val GroupProfilePhoto: GroupProfilePhoto,
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val id: String,
    val name: String,
    val published_at: String,
    val type: String?,
    val updatedAt: String,
    val users: List<String>
)