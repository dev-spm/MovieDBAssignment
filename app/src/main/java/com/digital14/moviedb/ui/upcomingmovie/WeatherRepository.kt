package com.digital14.moviedb.ui.upcomingmovie

import com.digital14.moviedb.network.MovieApiService
import javax.inject.Inject

class UpcomingMovieRepository @Inject constructor(
    private val movieApiService: MovieApiService
) {
    suspend fun fetchUpcomingMovies(apiKey: String) =
        movieApiService.getUpComingMovies(apiKey)
}