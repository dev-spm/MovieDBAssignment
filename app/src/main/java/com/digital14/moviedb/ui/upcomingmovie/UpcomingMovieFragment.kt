package com.digital14.moviedb.ui.upcomingmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.digital14.moviedb.BuildConfig
import com.digital14.moviedb.R
import com.digital14.moviedb.databinding.FragmentUpcomingMovieBinding
import com.digital14.moviedb.dto.MovieData
import com.digital14.moviedb.dto.MovieResponse
import com.digital14.moviedb.listeners.MovieClickListener
import com.digital14.moviedb.network.ResponseState
import com.digital14.moviedb.ui.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Sibaprasad Mohanty on 24/02/2022.
 * Spm Limited
 * siba.x.prasad@gmail.com
 */

@AndroidEntryPoint
class UpcomingMovieFragment : Fragment(), MovieClickListener {

    private val viewModel by viewModels<MovieViewModel>()
    private lateinit var binding: FragmentUpcomingMovieBinding
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming_movie, container, false)
        val view: View = binding.root
        binding.viewModel = viewModel
        binding.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchUpcomingMovies(BuildConfig.MOVIEDB_API_KEY)
        viewModel.responseStateObserver.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.Loading -> viewModel.obsevableLoading.set(true)
                is ResponseState.Success -> {
                    viewModel.obsevableLoading.set(false)
                }
                is ResponseState.Error ->{
                    viewModel.obsevableLoading.set(false)
                    viewModel.errorMessage.set(it.throwable.message)
                }
            }
        }
    }

    override fun onMovieClick(view: View, movieData: MovieData) {
        val action =
            UpcomingMovieFragmentDirections.actionUpcomingMovieFragmentToDetailsMovieFragment(
                movieData
            )
        findNavController(this).navigate(action)
    }
}