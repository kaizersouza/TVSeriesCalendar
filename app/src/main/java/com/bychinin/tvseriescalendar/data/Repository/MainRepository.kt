package com.bychinin.tvseriescalendar.data.Repository

import com.bychinin.tvseriescalendar.data.api.ApiHelper
import com.bychinin.tvseriescalendar.data.api.RoomSeries
import com.bychinin.tvseriescalendar.data.model.Series.Series

class MainRepository (private val apiHelper: ApiHelper, private val roomSeries: RoomSeries) {

    suspend fun getSeries(air_date_gte : String, air_date_lte : String) : Series {
        return if (roomSeries.isDatainRoom())
            roomSeries.getDataFromRoom()
        else {
            val series : Series = apiHelper.getSeries(air_date_gte, air_date_lte)
            roomSeries.writeToRoom(series)
            series
        }
    }

}