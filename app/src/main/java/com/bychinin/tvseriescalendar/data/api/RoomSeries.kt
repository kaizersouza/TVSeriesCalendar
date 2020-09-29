package com.bychinin.tvseriescalendar.data.api

import com.bychinin.tvseriescalendar.data.model.Series
import com.bychinin.tvseriescalendar.utils.Utils

class RoomSeries(private val air_date_gte : String,
                 private val air_date_lte : String
) {

    suspend fun isDatainRoom() : Boolean {
        return false
    }

    suspend fun getDataFromRoom() : Series {
        val series : Series = Series(0, mutableListOf(), 0, 0)

        return series
    }

    suspend fun writeToRoom(series: Series){
//        Utils.writeLog(series)
    }

}