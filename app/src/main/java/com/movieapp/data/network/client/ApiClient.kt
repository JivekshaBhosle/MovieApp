package com.movieapp.data.network.client

import com.google.gson.GsonBuilder
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.movieapp.BuildConfig
import com.movieapp.MovieAppApplication
import com.movieapp.data.network.client.interceptor.NetworkCacheInterceptor
import com.movieapp.data.network.client.interceptor.OfflineCacheInterceptor
import com.movieapp.data.network.service.ApiServiceUrls.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    fun getClientInterface(): ApiClientInterface {
        return getClient().create(ApiClientInterface::class.java)
    }

    private fun getClient(): Retrofit {
        val gsonBuilder = GsonBuilder()

        return Retrofit.Builder().client(createOkHttpClient())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .baseUrl(BASE_URL)
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {

        val httpClientBuilder = OkHttpClient().newBuilder()
            .cache(MovieAppApplication.applicationContext().cache())
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(logInterceptor())
        }

        httpClientBuilder.addInterceptor(NetworkCacheInterceptor())
        httpClientBuilder.addInterceptor(OfflineCacheInterceptor())
        return httpClientBuilder.build()
    }

    private fun logInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

}