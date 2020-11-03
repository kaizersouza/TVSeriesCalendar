package com.bychinin.tvseriescalendar.data.api.API

import com.bychinin.tvseriescalendar.data.api.API.RetrofitBuilder.apiService
import com.bychinin.tvseriescalendar.data.api.tmdb
import com.bychinin.tvseriescalendar.data.model.SeriesInfo.CreatedBy
import com.bychinin.tvseriescalendar.data.model.SeriesInfo.Genre
import com.bychinin.tvseriescalendar.data.model.SeriesInfo.SeriesInfo
import com.bychinin.tvseriescalendar.data.model.movie.MoviesInfo
import com.bychinin.tvseriescalendar.utils.Utils

class InfoHelper(private val id: Int){

    suspend fun getSeriesInfo(voute_count : Int) : SeriesInfo {

        var tmp : SeriesInfo = SeriesInfo("", mutableListOf(), emptyList(), "",
        mutableListOf(), "", 0, false, emptyList(), false,
        false, "", emptyList(), null, 0,
        0, emptyList(), "", "", "", 0.0,
        "", emptyList(), emptyList(), "", "", 0.0, 0)

        when (voute_count) {
            Utils.VOTE_COUNT -> {
                val moviesInfo : MoviesInfo = apiService.getMoviesInfo(id, tmdb.tmdb_API_KEY)
                tmp.name = moviesInfo.title
                tmp.overview = moviesInfo.overview
                tmp.vote_average = moviesInfo.vote_average ?: 0.0
                tmp.number_of_seasons = 0
                tmp.poster_path = moviesInfo.poster_path
                tmp.genres = moviesInfo.genres as MutableList<Genre>?
                moviesInfo.production_companies.forEach {
                    tmp.created_by.add(CreatedBy("", 0, 0, it.name, it.logo_path))
                }


            }
            else -> {tmp = apiService.getSeriesInfo(id, tmdb.tmdb_API_KEY)}
        }

        return tmp

    }

}
