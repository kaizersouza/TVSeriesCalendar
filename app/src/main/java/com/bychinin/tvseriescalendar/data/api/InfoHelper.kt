package com.bychinin.tvseriescalendar.data.api

import com.bychinin.tvseriescalendar.data.api.RetrofitBuilder.apiService
import com.bychinin.tvseriescalendar.data.model.SeriesInfo.SeriesInfo

class InfoHelper(private val id: Int){

    suspend fun getSeriesInfo() : SeriesInfo = apiService.getSeriesInfo(id, tmdb.tmdb_API_KEY)

}
