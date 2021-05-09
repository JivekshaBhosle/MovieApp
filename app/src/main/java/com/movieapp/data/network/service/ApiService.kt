package com.movieapp.data.network.service

import com.haroldadmin.cnradapter.NetworkResponse
import com.movieapp.data.network.client.ApiClient
import com.movieapp.data.network.model.DTOMovieDetail
import com.movieapp.data.network.model.base.DTOErrorResponse
import com.movieapp.data.network.model.response.DTOBaseResponseMovie

class ApiService : ApiServiceInterface {

    private val serviceClient = ApiClient().getClientInterface()

    override suspend fun getMovieLinks(): NetworkResponse<DTOBaseResponseMovie, DTOErrorResponse> {
        return serviceClient.getMovieLinks()
    }

    override suspend fun getMovieDetails(movieId: String): NetworkResponse<DTOMovieDetail, DTOErrorResponse> {
        return serviceClient.getMovieDetails(movieId)
    }

}