package com.movieapp.data.network.client

import com.haroldadmin.cnradapter.NetworkResponse
import com.movieapp.data.network.model.DTOMovieDetail
import com.movieapp.data.network.model.base.DTOErrorResponse
import com.movieapp.data.network.model.response.DTOBaseResponseMovie
import com.movieapp.data.network.service.ApiServiceUrls.MOVIE_DETAILS
import com.movieapp.data.network.service.ApiServiceUrls.MOVIE_LINKS
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClientInterface {

    //movie links
    /*=============================================*/
    @GET(MOVIE_LINKS)
    suspend fun getMovieLinks(): NetworkResponse<DTOBaseResponseMovie, DTOErrorResponse>


    //movie details
    /*=============================================*/
    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String
    ): NetworkResponse<DTOMovieDetail, DTOErrorResponse>

}