package com.movieapp.domain.model

import com.movieapp.domain.model.base.ErrorResponse

data class MovieDetail(

    var title: String = String(),
    var description: String = String(),
    var responseCode: MovieResponse = MovieResponse(),
    var errorResponse : ErrorResponse = ErrorResponse()
)