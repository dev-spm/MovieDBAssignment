package com.digital14.moviedb.ui.upcomingmovie

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.digital14.moviedb.network.MovieApiService
import com.digital14.moviedb.util.MockResponseFileReader
import com.digital14.moviedb.utility.NetworkHelper
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class MoviesApiTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: MovieApiService
    private var networkHelper: NetworkHelper? = null

    private val server = MockWebServer()
    private lateinit var repository: UpcomingMovieRepository
    private lateinit var viewModel: MovieViewModel
    private var context: Context = Mockito.mock(Context::class.java)
    private lateinit var mockedResponse: String

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    @Before
    fun init() {
        networkHelper = NetworkHelper(context)
        MockitoAnnotations.initMocks(this)
        server.start(8000)

        val BASE_URL = server.url("/").toString()

        val okHttpClient = OkHttpClient
            .Builder()
            .build()
        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build().create(MovieApiService::class.java)

        repository = UpcomingMovieRepository(service)

        val repository = UpcomingMovieRepository(api)
        viewModel = MovieViewModel(context, repository, networkHelper!!)
    }

    @Test
    fun fetchUpcomingMovies_success_response() {

        mockedResponse = MockResponseFileReader("movies/success.json").content

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )

        val response = runBlocking { repository.fetchUpcomingMovies() }
        val json = gson.toJson(response.body())

        val resultResponse = JsonParser().parse(json)
        val expectedresponse = JsonParser().parse(mockedResponse)

        Assert.assertNotNull(response)
        Assert.assertTrue(resultResponse.equals(expectedresponse))
    }

    @Test
    fun fetchUpcomingMovies_fail() {

        mockedResponse = MockResponseFileReader("movies/fail.json").content

        server.enqueue(
            MockResponse()
                .setResponseCode(400)
                .setBody(mockedResponse)
        )

        val response = runBlocking { repository.fetchUpcomingMovies("wrongapikey") }
        Assert.assertNotNull(response)
        Assert.assertTrue(response.message().equals("Client Error"))
        Assert.assertTrue(response.code() == 400)
    }

    @Test
    fun fetchUpcomingMoviesApi404() {
        mockedResponse = MockResponseFileReader("movies/fail.json").content
        server.enqueue(
            MockResponse()
                .setResponseCode(404)
                .setBody(mockedResponse)
        )
        val response = runBlocking { repository.fetchUpcomingMovies("wrongapikey") }
        Assert.assertNotNull(response)
        Assert.assertTrue(response.message().equals("Client Error"))
        Assert.assertTrue(response.code() == 404)
    }

    @Test
    fun fetchUpcomingMoviesApi500() {
        mockedResponse = MockResponseFileReader("movies/fail.json").content
        server.enqueue(
            MockResponse()
                .setResponseCode(500)
                .setBody(mockedResponse)
        )
        val response = runBlocking { repository.fetchUpcomingMovies("wrongapikey") }
        Assert.assertNotNull(response)
        Assert.assertTrue(response.message().equals("Server Error"))
        Assert.assertTrue(response.code() == 500)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}