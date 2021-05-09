package com.movieapp.data.network.model

import com.google.gson.annotations.SerializedName

data class DTOMovieSection(

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("href")
    val href: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("templated")
    val templated: Boolean? = null,

    @SerializedName("active")
    val active: Boolean? = null


)
