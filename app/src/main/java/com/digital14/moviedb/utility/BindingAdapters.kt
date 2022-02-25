package com.digital14.moviedb.utility

import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.digital14.moviedb.BuildConfig
import com.digital14.moviedb.R
import com.digital14.moviedb.dto.MovieData
import com.digital14.moviedb.ui.adapter.MovieAdapter


/**
 * Created by Sibaprasad Mohanty on 24/02/2022.
 * Spm Limited
 * siba.x.prasad@gmail.com
 */

object BindingAdapters {

    @JvmStatic
    @BindingAdapter(value = ["setAdapter"])
    fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
        this.run {
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["updateMovies"])
    fun RecyclerView.updateRecyclerViewAdapter(
        movieList: MutableList<MovieData>?
    ) {
        val adapter = this.adapter as? MovieAdapter?
        if (movieList != null) {
            adapter?.setMovies(movieList)
        }
    }

    @JvmStatic
    @BindingAdapter("movieImage")
    fun loadImage(imageview: AppCompatImageView, imageUrl: String?) {
        val fullImageUrl = BuildConfig.BASE_IMAGE_URL + imageUrl
        Log.i("TAG", fullImageUrl)

        Glide
            .with(imageview.context)
            .load(fullImageUrl)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .into(imageview)
    }

    @JvmStatic
    @BindingAdapter("movielayoutmanager")
    fun setLayoutManager(recyclerView: RecyclerView, spanCount: Int) {
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, spanCount)
    }

    @JvmStatic
    @BindingAdapter("show")
    fun showOrHideProgress(view: View, showHideStatus: Boolean) {
        view.visibility = if (showHideStatus) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("errorVisibleGone")
    fun showOrHideError(view: View, error: String) {
        view.visibility = if (error.isEmpty()) View.GONE else View.VISIBLE
    }

    @JvmStatic
    @BindingAdapter("errorMessage")
    fun showErrorMessage(view: AppCompatTextView, message: String) {
        message.let {
            view.text = message
        }
    }

    @set:BindingAdapter("invisible")
    var View.invisible
        get() = visibility == View.INVISIBLE
        set(value) {
            visibility = if (value) View.INVISIBLE else View.VISIBLE
        }

    @set:BindingAdapter("gone")
    var View.gone
        get() = visibility == View.GONE
        set(value) {
            visibility = if (value) View.GONE else View.VISIBLE
        }


    @JvmStatic
    @BindingAdapter("weatherDescription")
    fun showWeatherDescription(view: AppCompatTextView, message: String) {
        message.let {
            view.text = message
        }
    }

    @JvmStatic
    @BindingAdapter("textChangedListener")
    fun bindTextWatcher(editText: EditText, textWatcher: TextWatcher) {
        editText.addTextChangedListener(textWatcher)
    }

    @JvmStatic
    @BindingAdapter("onRefreshListener")
    fun setSwipeRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout, isRefreshing: Boolean) {
        swipeRefreshLayout.isRefreshing = isRefreshing
    }

}