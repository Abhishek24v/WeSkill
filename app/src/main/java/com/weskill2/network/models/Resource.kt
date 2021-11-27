package com.weskill2.network.models

sealed class Resource<T : Any> {
    class Success<T : Any>(val r: T, val msg: String = "") : Resource<T>()
    class Error<T : Any>(val msg: String) : Resource<T>()
    class Loading<T : Any>() : Resource<T>()
}