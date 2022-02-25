package com.digital14.moviedb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digital14.moviedb.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Sibaprasad Mohanty on 24/02/2022.
 * Spm Limited
 * siba.x.prasad@gmail.com
 */

@AndroidEntryPoint
class MovieDbActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moviedb)
    }
}