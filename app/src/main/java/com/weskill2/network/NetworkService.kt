package com.weskill2.network

import com.weskill.models.reset_password.ForgotPassword
import com.weskill.models.reset_password.ForgotPasswordResponse
import com.weskill2.helper.getTrials
import com.weskill2.helper.login
import com.weskill2.helper.forgotPassword
import com.weskill2.network.models.LoginUserModel
import com.weskill2.network.models.likes_and_comments.CommentsResponse
import com.weskill2.network.models.trials.TrialsResponse
import com.weskill2.network.models.user.LoginUserResponse
import retrofit2.Response
import retrofit2.http.*

interface NetworkService {

    @GET(getTrials)
    suspend fun getTrials(): Response<ArrayList<TrialsResponse>>

    @GET("/comments/")
    suspend fun getComments(@Query("post") postId: String): Response<ArrayList<CommentsResponse>>

    @Headers("Content-Type: application/json")
    @POST(login)
    suspend fun loginUser(@Body registerUserModel: LoginUserModel): Response<LoginUserResponse>

    @POST(forgotPassword)
    suspend fun forgotPassword(@Body m: ForgotPassword): Response<ForgotPasswordResponse>

}