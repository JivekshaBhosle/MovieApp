package com.movieapp.data.model

import com.example.bingeit.data.TestUtil
import com.movieapp.data.network.model.TransformerMovie.transformMovieResponse
import com.movieapp.data.network.model.response.DTOBaseResponseMovie
import org.junit.Assert
import org.junit.Test

class TransformerMovieTest {

    companion object {
        private val response: DTOBaseResponseMovie = TestUtil.readJson(
            TransformerMovieTest::class.java.classLoader,
            "api-response/movies.json",
            DTOBaseResponseMovie::class.java
        )
    }

    @Test
    fun isMovieSectionsNotEmpty() {
        val movieListResponse = response.transformMovieResponse()
        val movieSections = movieListResponse.links.sections
        Assert.assertTrue(movieSections.isNotEmpty())
    }

    @Test
    fun transformMovieSectionTitle() {
        val movieListResponse = response.transformMovieResponse()
        val movieSectionTitle = movieListResponse.links.sections[0].title
        Assert.assertEquals("Serier", movieSectionTitle)
    }

    @Test
    fun transformMovieSectionDetailsLink() {
        val movieListResponse = response.transformMovieResponse()
        val movieSectionHref = movieListResponse.links.sections[0].href
        Assert.assertEquals(
            "https://content.viaplay.se/androiddash-se/serier{?dtg}",
            movieSectionHref
        )
    }
}