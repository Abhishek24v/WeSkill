package com.weskill2.network.repository

import com.weskill2.network.NetworkService
import com.weskill2.network.SafeApiRequest
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val networkApi: NetworkService) :
    SafeApiRequest() {

    suspend fun getTrials() = apiRequest { networkApi.getTrials() }

}