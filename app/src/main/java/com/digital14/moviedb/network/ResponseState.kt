package com.digital14.moviedb.network


/**
 * Created by Sibaprasad Mohanty on 25/02/2022.
 * Spm Limited
 * sp.dobest@gmail.com
 */

sealed class ResponseState {
    data class Loading(val isLoading : Boolean) : ResponseState()
    data class Error(val throwable: Throwable) : ResponseState()
    data class Success(val data: Any) : ResponseState()
}