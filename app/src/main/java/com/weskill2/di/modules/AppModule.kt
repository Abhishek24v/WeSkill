package com.weskill2.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.weskill2.R
import com.weskill2.helper.baseUrl
import com.weskill2.network.NetworkService
import com.weskill2.network.repository.NetworkRepository
import com.weskill2.network.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit {
        val client = OkHttpClient.Builder().connectTimeout(0, TimeUnit.SECONDS).readTimeout(
            0,
            TimeUnit.SECONDS
        ).writeTimeout(0, TimeUnit.SECONDS).addNetworkInterceptor(StethoInterceptor())

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)

    @Provides
    @Singleton
    fun provideNetworkRepository(networkService: NetworkService) = NetworkRepository(networkService)

    @Provides
    @Singleton
    fun providePostRepository(networkService: NetworkService) = PostRepository(networkService)

}