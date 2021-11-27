package com.weskill2.network.models.user

data class Certificate(
    val __v: Int,
    val _id: String,
    val alternativeText: String,
    val caption: String,
    val createdAt: String,
    val ext: String,
    val formats: FormatsX,
    val hash: String,
    val height: Int,
    val id: String,
    val mime: String,
    val name: String,
    val provider: String,
    val provider_metadata: ProviderMetadataXXXXXXX,
    val related: List<String>,
    val size: Double,
    val updatedAt: String,
    val url: String,
    val width: Int
)