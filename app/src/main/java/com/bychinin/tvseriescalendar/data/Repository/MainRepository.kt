package com.bychinin.tvseriescalendar.data.Repository

import com.bychinin.tvseriescalendar.data.api.ApiHelper
import com.bychinin.tvseriescalendar.data.api.RoomSeries
import com.bychinin.tvseriescalendar.data.model.Series

class MainRepository (private val apiHelper: ApiHelper, private val roomSeries: RoomSeries) {

    suspend fun getSeries() : Series {
        return if (roomSeries.isDatainRoom())
            roomSeries.getDataFromRoom()
        else {
            val series : Series = apiHelper.getSeries()
            roomSeries.writeToRoom(series)
            series
        }
    }

}