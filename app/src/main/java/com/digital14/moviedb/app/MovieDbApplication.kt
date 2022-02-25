package com.digital14.moviedb.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Sibaprasad Mohanty on 24/02/2022.
 * Spm Limited
 * siba.x.prasad@gmail.com
 */

@HiltAndroidApp
class MovieDbApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
