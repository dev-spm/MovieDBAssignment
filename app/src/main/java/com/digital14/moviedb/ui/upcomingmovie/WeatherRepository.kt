package com.digital14.moviedb.ui.upcomingmovie

import com.digital14.moviedb.BuildConfig
import com.digital14.moviedb.network.MovieApiService
import javax.inject.Inject

class UpcomingMovieRepository @Inject constructor(
    private val movieApiService: MovieApiService
) {
    suspend fun fetchUpcomingMovies(apiKey: String = BuildConfig.MOVIEDB_API_KEY) =
        movieApiService.getUpComingMovies(apiKey)
}