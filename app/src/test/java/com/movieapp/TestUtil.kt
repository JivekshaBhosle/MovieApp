package com.example.bingeit.data

import com.google.gson.Gson
import java.io.FileNotFoundException
import java.io.IOException
import java.net.URL

object TestUtil {

    fun <T> readJson(classLoader: ClassLoader?, jsonFileName: String, resultClass: Class<T>): T {
        val gson = Gson()
        val json: URL =
            classLoader?.getResource(jsonFileName) ?: throw FileNotFoundException(jsonFileName)
        return gson.fromJson(json.readText(Charsets.UTF_8), resultClass)
            ?: throw IOException("Invalid JSON: $jsonFileName")
    }

}