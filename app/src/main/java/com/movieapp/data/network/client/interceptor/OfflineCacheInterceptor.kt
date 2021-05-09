package com.movieapp.data.network.client.interceptor

import com.movieapp.MovieAppApplication
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class OfflineCacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val cacheControl = CacheControl.Builder()
            .maxStale(7, TimeUnit.DAYS)
            .build()

        if (!MovieAppApplication.applicationContext().isNetworkConnected()) {
            request = request.newBuilder()
                .removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl)
                .build()
        }

        return chain.proceed(request)
    }
}