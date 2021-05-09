package com.movieapp.data.network.model

import com.google.gson.annotations.SerializedName

data class DTOMovieResponse(

    @SerializedName("httpStatus")
    val httpStatus: String? = null,

    @SerializedName("statusCode")
    val statusCode: String? = null,

    @SerializedName("code")
    val code: String? = null

)