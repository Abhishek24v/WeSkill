package com.weskill2.network.models.user

data class Medium(
    val ext: String,
    val hash: String,
    val height: Int,
    val mime: String,
    val name: String,
    val path: Any,
    val provider_metadata: ProviderMetadataXXXX,
    val size: Double,
    val url: String,
    val width: Int
)