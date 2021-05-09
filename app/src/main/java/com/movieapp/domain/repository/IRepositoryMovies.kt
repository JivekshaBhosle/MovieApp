package com.movieapp.domain.repository

import com.movieapp.domain.model.MovieDetail
import com.movieapp.domain.model.response.BaseMovieResponse

interface IRepositoryMovies {

    suspend fun getMovieLinks(): BaseMovieResponse

    suspend fun getMovieDetails(movieId: String): MovieDetail

}