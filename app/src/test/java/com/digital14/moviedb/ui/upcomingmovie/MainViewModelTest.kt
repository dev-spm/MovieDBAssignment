package com.digital14.moviedb.ui.upcomingmovie

import android.content.Context
import com.digital14.moviedb.BuildConfig
import com.digital14.moviedb.network.MovieApiService
import com.digital14.moviedb.network.ResponseState
import com.digital14.moviedb.utility.NetworkHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineScope()

    @Mock
    private lateinit var api: MovieApiService

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
//        mockkStatic("com.android.sample.tvmaze.util.ContextExtKt")
//        every {
//            networkHelper.isNetworkConnected()
//        } returns true

        testCoroutineRule.runBlockingTest {
            `when`(api.getUpComingMovies(BuildConfig.MOVIEDB_API_KEY)).thenReturn(null)
        }

        val repository = UpcomingMovieRepository(api)

        testCoroutineRule.pauseDispatcher()

        val viewModel = MovieViewModel(context, repository, networkHelper)

        assertThat(viewModel.responseStateObserver.value, `is`(ResponseState.Loading(true)))

        testCoroutineRule.resumeDispatcher()
    }
}