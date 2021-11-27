package com.weskill2.network.models.user


data class UserGroup (
    val user: String,
    val groups : List<Group>
    )