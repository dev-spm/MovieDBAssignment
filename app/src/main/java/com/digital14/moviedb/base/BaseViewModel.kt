package com.digital14.moviedb.base

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.digital14.moviedb.dto.MovieData
import com.digital14.moviedb.dto.MovieResponse

open class BaseViewModel : ViewModel(), Observable {

    var obsevableLoading = ObservableBoolean(false)
    var loadMore = ObservableBoolean(false)
    var errorMessage = ObservableField("")

    fun isValidMovieData(movieResponse: MovieResponse?): Boolean {
        errorMessage.get()?.isNotEmpty()
        return (movieResponse != null
                && movieResponse.results.isNotEmpty())
    }

    fun isValidMovieList(listMovie: List<MovieData>) =
        listMovie.isNotEmpty()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}