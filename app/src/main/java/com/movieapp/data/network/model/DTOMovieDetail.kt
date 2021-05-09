package com.movieapp.data.network.model

import com.google.gson.annotations.SerializedName

data class DTOMovieDetail(

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("responseCode")
    val responseCode: DTOMovieResponse? = null

)