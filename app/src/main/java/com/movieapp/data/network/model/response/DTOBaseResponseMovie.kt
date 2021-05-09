package com.movieapp.data.network.model.response

import com.google.gson.annotations.SerializedName
import com.movieapp.data.network.model.DTOMovieLinks
import com.movieapp.data.network.model.DTOMovieResponse

data class DTOBaseResponseMovie(

    @SerializedName("_links")
    val _links: DTOMovieLinks? = null,

    @SerializedName("responseCode")
    val responseCode: DTOMovieResponse? = null
)