package com.digital14.moviedb.dto

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


/**
 * Created by Sibaprasad Mohanty on 24/02/2022.
 * Spm Limited
 * siba.x.prasad@gmail.com
 */

@Keep
@Parcelize
data class MovieResponse( val dates: Dates,
                          val page: Int, // 1
                          val results: List<MovieData>,
                          val total_pages: Int, // 17
                          val total_results: Int // 340
                          ): Parcelable

@Keep
@Parcelize
data class Dates(
    val maximum: String, // 2022-03-23
    val minimum: String // 2022-03-02
): Parcelable

@Keep
@Parcelize
data class MovieData(
    val adult: Boolean, // false
    val backdrop_path: String, // /tutaKitJJIaqZPyMz7rxrhb4Yxm.jpg
    val genre_ids: List<Int>,
    val id: Int, // 438695
    val original_language: String, // en
    val original_title: String, // Sing 2
    val overview: String, // Buster and his new cast now have their sights set on debuting a new show at the Crystal Tower Theater in glamorous Redshore City. But with no connections, he and his singers must sneak into the Crystal Entertainment offices, run by the ruthless wolf mogul Jimmy Crystal, where the gang pitches the ridiculous idea of casting the lion rock legend Clay Calloway in their show. Buster must embark on a quest to find the now-isolated Clay and persuade him to return to the stage.
    val popularity: Double, // 1452.242
    val poster_path: String, // /aWeKITRFbbwY8txG5uCj4rMCfSP.jpg
    val release_date: String, // 2021-12-01
    val title: String, // Sing 2
    val video: Boolean, // false
    val vote_average: Double, // 8.2
    val vote_count: Int // 1976
) : Parcelable