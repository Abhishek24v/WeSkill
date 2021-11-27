package com.weskill2.network.repository

import com.weskill2.network.NetworkService
import com.weskill2.network.SafeApiRequest
import javax.inject.Inject

class PostRepository @Inject constructor(private val networkApi: NetworkService) :
    SafeApiRequest() {

    suspend fun getComments(postId: String) = apiRequest { networkApi.getComments(postId) }

}