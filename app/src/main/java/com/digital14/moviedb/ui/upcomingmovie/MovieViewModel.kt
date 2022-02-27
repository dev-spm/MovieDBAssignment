package com.digital14.moviedb.ui.upcomingmovie

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.digital14.moviedb.base.BaseViewModel
import com.digital14.moviedb.dto.MovieData
import com.digital14.moviedb.dto.MovieResponse
import com.digital14.moviedb.network.ResponseState
import com.digital14.moviedb.utility.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by Sibaprasad Mohanty on 24/02/2022.
 * Spm Limited
 * siba.x.prasad@gmail.com
 */

@HiltViewModel
class MovieViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val upcomingMovieRepository: UpcomingMovieRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    companion object {
        const val TAG = "MovieViewModel"
    }

    var movieList = ObservableField<MutableList<MovieData>>()
    var responseStateObserver = MutableLiveData<ResponseState>()

    fun fetchUpcomingMovies() {
        try {
            if (networkHelper.isNetworkConnected()) {
                responseStateObserver.value = ResponseState.Loading(true)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val data = upcomingMovieRepository.fetchUpcomingMovies()
                        onResponseFromServer(data)
                    } catch (e: Exception) {
                        Log.i("TAG", e.message.toString())
                        responseStateObserver.value =
                            ResponseState.Error(Throwable("Error Occurred!"))
                    }
                }
            } else {
                responseStateObserver.value =
                    ResponseState.Error(Throwable("No Internet Connection!"))
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
            }
        } catch (exception: Exception) {
            responseStateObserver.value = ResponseState.Error(exception)
        }
    }

    private fun onResponseFromServer(response: Response<MovieResponse>) {
        if (response.isSuccessful) {
            val moviesResponse: MovieResponse? = response.body()
            moviesResponse?.let { movieRes ->
                Log.i("TAG", "${movieRes.results}")
                responseStateObserver.postValue( ResponseState.Success(movieRes.results as MutableList<MovieData>))
            }
        } else {
            responseStateObserver.value = ResponseState.Error(Throwable("Error Occurred!"))
        }
    }
}