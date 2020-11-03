package com.bychinin.tvseriescalendar.data.Repository

import com.bychinin.tvseriescalendar.data.api.API.ApiHelper
import com.bychinin.tvseriescalendar.data.api.Cash.CashedHelper
import com.bychinin.tvseriescalendar.data.model.Series.Series

class MainRepository (private val apiHelper: ApiHelper, private val cashedHelper: CashedHelper) {

    suspend fun getAllFromCash(air_date_gte : String) : Series = cashedHelper.getAllFromCash(air_date_gte)

    suspend fun getSeriesFromNet(air_date_gte : String, air_date_lte : String) : Series {
            val series : Series = apiHelper.getSeries(air_date_gte, air_date_lte)
            cashedHelper.writeAllToCash(series, air_date_gte)
            val movies : Series = apiHelper.getMovies(air_date_gte, air_date_lte)
            cashedHelper.writeAllToCash(movies, air_date_gte)
            movies.results.forEach {
                series.results.add(it)
            }
            return series
    }

}