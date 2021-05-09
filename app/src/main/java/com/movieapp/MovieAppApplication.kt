package com.movieapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import okhttp3.Cache

class MovieAppApplication : Application() {

    companion object {
        var instance: MovieAppApplication? = null

        fun applicationContext(): MovieAppApplication {
            return instance as MovieAppApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun cache(): Cache {
        val cacheSize = 5 * 1024 * 1024.toLong()
        return Cache(instance!!.applicationContext.cacheDir, cacheSize)
    }

    fun isNetworkConnected(): Boolean {
        var isConnected = false

        kotlin.runCatching {
            val connectivityManager =
                instance!!.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                connectivityManager?.registerDefaultNetworkCallback(object : NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        isConnected = true
                    }

                    override fun onLost(network: Network) {
                        isConnected = false
                    }
                })
            } else {
                val capability =
                    connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
                return capability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    ?: false

            }
        }

        return isConnected
    }


}