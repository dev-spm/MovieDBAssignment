package com.digital14.moviedb.network

import com.digital14.moviedb.dto.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Sibaprasad Mohanty on 24/02/2022.
 * Spm Limited
 * siba.x.prasad@gmail.com
 */

interface MovieApiService {
    @GET("upcoming")
    suspend fun getUpComingMovies(
        @Query("api_key") apiKey: String,
    ): Response<MovieResponse>
}