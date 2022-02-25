package com.digital14.moviedb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.digital14.moviedb.databinding.ItemviewMovieBinding
import com.digital14.moviedb.dto.MovieData
import com.digital14.moviedb.BR
import com.digital14.moviedb.listeners.MovieClickListener

/**
 * Created by Sibaprasad Mohanty on 24/02/2022.
 * Spm Limited
 * siba.x.prasad@gmail.com
 */

class MovieAdapter(
     private val movieClickListener: MovieClickListener
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList: MutableList<MovieData> = ArrayList()

    class MovieViewHolder(private val binding: ItemviewMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: MovieData,movieClickListener : MovieClickListener ) {
            binding.setVariable(BR.movie, obj)
            binding.setVariable(BR.listener, movieClickListener)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val movieBinding: ItemviewMovieBinding =
            ItemviewMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(movieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item, movieClickListener)
    }

    override fun getItemCount() = differ.currentList.size

    private val MOVIE_DIFF_CALLBACK: DiffUtil.ItemCallback<MovieData> =
        object : DiffUtil.ItemCallback<MovieData>() {
            override fun areItemsTheSame(
                @NonNull oldMovie: MovieData,
                @NonNull newMovie: MovieData
            ): Boolean {
                return oldMovie.id == newMovie.id
            }

            override fun areContentsTheSame(
                @NonNull oldMovie: MovieData,
                @NonNull newMovie: MovieData
            ): Boolean {
                return oldMovie.id == newMovie.id
            }
        }

    private val differ: AsyncListDiffer<MovieData> =
        AsyncListDiffer(this, MOVIE_DIFF_CALLBACK)

    fun setMovies(movies: MutableList<MovieData>) {
        if (movies.isNotEmpty()) {
            movieList = movies
            differ.submitList(movies)
        }
    }
}