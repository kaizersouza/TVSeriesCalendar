package com.bychinin.tvseriescalendar.data.api

import com.bychinin.tvseriescalendar.data.api.tmdb.tmdb_NetworkArray
import com.bychinin.tvseriescalendar.data.model.Series

class ApiHelper(private val apiService: ApiService,
                private val air_date_gte : String,
                private val air_date_lte : String
                ) {

    suspend fun getSeries() : Series {
        var series : Series = Series(0, mutableListOf(), 0, 0)

        tmdb_NetworkArray.forEach {
            val tmp : Series = apiService.getSeries(tmdb.tmdb_API_KEY, it.id, air_date_gte, air_date_lte)
            tmp.results.forEach {
                series.results.add(it)
            }
        }

        return series
    }

}