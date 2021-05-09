package com.movieapp.data.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.movieapp.data.network.model.TransformerMovie.transformError
import com.movieapp.data.network.model.TransformerMovie.transformErrorResponse
import com.movieapp.data.network.model.TransformerMovie.transformMovieDetail
import com.movieapp.data.network.model.TransformerMovie.transformMovieResponse
import com.movieapp.data.network.service.ApiService
import com.movieapp.domain.model.MovieDetail
import com.movieapp.domain.model.base.ErrorResponse
import com.movieapp.domain.model.response.BaseMovieResponse
import com.movieapp.domain.repository.IRepositoryMovies

class RepositoryMoviesImpl : IRepositoryMovies {

    private val apiService = ApiService()

    override suspend fun getMovieLinks(): BaseMovieResponse {

        when (val apiResponse = apiService.getMovieLinks()) {
            is NetworkResponse.Success -> {
                return apiResponse.body.transformMovieResponse()
            }
            is NetworkResponse.ServerError -> {
                return BaseMovieResponse(
                    errorResponse = apiResponse.body?.transformErrorResponse() ?: ErrorResponse()
                )
            }
            is NetworkResponse.Error -> {
                return BaseMovieResponse(
                    errorResponse = apiResponse.error.transformError()
                )
            }
            is NetworkResponse.NetworkError -> {
                return BaseMovieResponse(
                    errorResponse = apiResponse.error.transformError()
                )
            }
            is NetworkResponse.UnknownError -> {
                return BaseMovieResponse(
                    errorResponse = apiResponse.error.transformError()
                )
            }
        }
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetail {

        when (val apiResponse = apiService.getMovieDetails(movieId)) {
            is NetworkResponse.Success -> {
                return apiResponse.body.transformMovieDetail()
            }
            is NetworkResponse.ServerError -> {
                return MovieDetail(
                    errorResponse = apiResponse.body?.transformErrorResponse() ?: ErrorResponse()
                )
            }
            is NetworkResponse.Error -> {
                return MovieDetail(
                    errorResponse = apiResponse.error.transformError()
                )
            }
            is NetworkResponse.NetworkError -> {
                return MovieDetail(
                    errorResponse = apiResponse.error.transformError()
                )
            }
            is NetworkResponse.UnknownError -> {
                return MovieDetail(
                    errorResponse = apiResponse.error.transformError()
                )
            }
        }
    }

}