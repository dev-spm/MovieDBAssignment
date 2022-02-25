package com.digital14.moviedb.listeners

import android.view.View
import com.digital14.moviedb.dto.MovieData


/**
 * Created by Sibaprasad Mohanty on 25/02/2022.
 * Spm Limited
 * sp.dobest@gmail.com
 */

interface MovieClickListener {
    fun onMovieClick(view : View, movieData: MovieData)
}