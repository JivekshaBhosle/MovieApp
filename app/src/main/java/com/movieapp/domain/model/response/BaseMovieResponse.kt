package com.movieapp.domain.model.response

import com.movieapp.domain.model.MovieLinks
import com.movieapp.domain.model.MovieResponse
import com.movieapp.domain.model.base.ErrorResponse

data class BaseMovieResponse(
    var links: MovieLinks = MovieLinks(),
    var responseCode: MovieResponse = MovieResponse(),
    var errorResponse : ErrorResponse = ErrorResponse()
)