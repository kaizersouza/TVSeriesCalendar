package com.bychinin.tvseriescalendar.data.Repository

import com.bychinin.tvseriescalendar.data.api.ApiHelper
import com.bychinin.tvseriescalendar.data.api.RoomHelper
import com.bychinin.tvseriescalendar.data.model.Series.Series

class MainRepository (private val apiHelper: ApiHelper, private val RoomHelper: RoomHelper) {

    suspend fun getSeries(air_date_gte : String, air_date_lte : String) : Series {
        return if (RoomHelper.isDatainRoom(air_date_gte))
            RoomHelper.getDataFromRoom(air_date_gte)
        else {
            val series : Series = apiHelper.getSeries(air_date_gte, air_date_lte)
            RoomHelper.writeToRoom(series)
            series
        }
    }

}