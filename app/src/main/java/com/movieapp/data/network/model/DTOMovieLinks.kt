package com.movieapp.data.network.model

import com.google.gson.annotations.SerializedName

data class DTOMovieLinks(

    @SerializedName("viaplay:sections")
    val sections: List<DTOMovieSection>? = null

)