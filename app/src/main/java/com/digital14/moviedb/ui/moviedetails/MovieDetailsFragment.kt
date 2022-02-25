package com.digital14.moviedb.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.digital14.moviedb.R
import com.digital14.moviedb.databinding.FragmentMovieDetailsBinding
import com.digital14.moviedb.dto.MovieData

/**
 * Created by Sibaprasad Mohanty on 24/02/2022.
 * Spm Limited
 * siba.x.prasad@gmail.com
 */

class MovieDetailsFragment : Fragment() {

    private val viewModel by viewModels<MovieDetailsViewModel>()
    private lateinit var binding: FragmentMovieDetailsBinding
    private var movieData: MovieData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieData = it.getParcelable<MovieData>("movieData")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        val view: View = binding.root
        binding.viewModel = viewModel
        binding.movie = movieData
        return view
    }
}