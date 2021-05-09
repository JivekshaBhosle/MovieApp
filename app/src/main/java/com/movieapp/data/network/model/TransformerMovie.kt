package com.movieapp.data.network.model

import com.movieapp.data.network.model.base.DTOErrorResponse
import com.movieapp.data.network.model.response.DTOBaseResponseMovie
import com.movieapp.domain.model.MovieDetail
import com.movieapp.domain.model.MovieLinks
import com.movieapp.domain.model.MovieResponse
import com.movieapp.domain.model.MovieSection
import com.movieapp.domain.model.base.ErrorResponse
import com.movieapp.domain.model.response.BaseMovieResponse

object TransformerMovie {

    fun DTOBaseResponseMovie.transformMovieResponse() = BaseMovieResponse().apply {
        links = this@transformMovieResponse._links?.transformMovieLinks() ?: MovieLinks()
        responseCode =
            this@transformMovieResponse.responseCode?.transformMovieResponse() ?: MovieResponse()
    }

    fun DTOMovieDetail.transformMovieDetail() = MovieDetail().apply {
        title = this@transformMovieDetail.title ?: title
        description = this@transformMovieDetail.description ?: description
        responseCode =
            this@transformMovieDetail.responseCode?.transformMovieResponse() ?: responseCode
    }

    fun DTOErrorResponse.transformErrorResponse() = ErrorResponse().apply {
        message = this@transformErrorResponse.message
    }

    fun Throwable.transformError() = ErrorResponse().apply {
        message = this@transformError.message ?: String()
    }

    private fun DTOMovieResponse.transformMovieResponse() = MovieResponse().apply {
        httpStatus = this@transformMovieResponse.httpStatus ?: httpStatus
        statusCode = this@transformMovieResponse.statusCode ?: statusCode
        code = this@transformMovieResponse.code ?: code
    }

    private fun DTOMovieLinks.transformMovieLinks() = MovieLinks().apply {
        sections = ArrayList<MovieSection>().apply {
            this@transformMovieLinks.sections?.forEach {
                add(it.transformMovieSection())
            }
        }
    }

    private fun DTOMovieSection.transformMovieSection() = MovieSection().apply {
        id = this@transformMovieSection.id ?: id
        title = this@transformMovieSection.title ?: title
        href = this@transformMovieSection.href ?: href
        type = this@transformMovieSection.type ?: type
        name = this@transformMovieSection.name ?: name
        templated = this@transformMovieSection.templated ?: templated
        active = this@transformMovieSection.active ?: active
    }
}