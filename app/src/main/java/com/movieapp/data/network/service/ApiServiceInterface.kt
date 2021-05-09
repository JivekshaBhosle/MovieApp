package com.movieapp.data.network.service

import com.haroldadmin.cnradapter.NetworkResponse
import com.movieapp.data.network.model.DTOMovieDetail
import com.movieapp.data.network.model.base.DTOErrorResponse
import com.movieapp.data.network.model.response.DTOBaseResponseMovie

interface ApiServiceInterface {

    suspend fun getMovieLinks(): NetworkResponse<DTOBaseResponseMovie, DTOErrorResponse>

    suspend fun getMovieDetails(movieId: String): NetworkResponse<DTOMovieDetail, DTOErrorResponse>
}