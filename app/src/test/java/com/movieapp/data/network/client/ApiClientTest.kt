package com.movieapp.data.network.client

import com.google.gson.GsonBuilder
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.movieapp.data.network.model.TransformerMovie.transformMovieResponse
import com.movieapp.data.repository.RepositoryMoviesImpl
import com.movieapp.domain.model.MovieLinks
import com.movieapp.domain.model.MovieSection
import com.movieapp.domain.model.response.BaseMovieResponse
import com.movieapp.domain.usecase.UseCaseMovieLinksGet
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClientTest {
    private val mockWebServer = MockWebServer()
    private lateinit var client: ApiClientInterface

    internal lateinit var underTest: UseCaseMovieLinksGet

    private val repositoryImpl = mockk<RepositoryMoviesImpl>(relaxed = true)


    @Before
    fun createService() {
        client = Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create()
                )
            )
            .build()
            .create(ApiClientInterface::class.java)

        underTest = UseCaseMovieLinksGet(repositoryImpl) {}
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should make movies api call`() = runBlocking {

        enqueueResponse("movies.json")

        val movieListResponse = BaseMovieResponse(
            links = MovieLinks(
                sections = listOf(
                    MovieSection(
                        id = "35bb8a90-d40e-11e2-8b8b-0800200c9a66",
                        title = "Serier",
                        href = "https://content.viaplay.se/androiddash-se/serier{?dtg}",
                        type = "vod",
                        name = "series",
                        templated = true
                    )
                )
            )
        )

        when (val apiResponse = client.getMovieLinks()) {
            is NetworkResponse.Success -> {
                val response = apiResponse.body.transformMovieResponse()
                Assert.assertEquals(movieListResponse.links.sections[0], response.links.sections[0])
            }
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()

        val mockResponse = MockResponse()

        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }

        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}

