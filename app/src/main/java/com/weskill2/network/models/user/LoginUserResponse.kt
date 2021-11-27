package com.weskill2.network.models.user

data class LoginUserResponse(
    val jwt: String,
    val user: User
)