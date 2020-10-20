package com.bychinin.tvseriescalendar.data.Repository

import com.bychinin.tvseriescalendar.data.api.InfoHelper
import com.bychinin.tvseriescalendar.data.api.RoomInfo

class InfoRepository (private val infoHelper: InfoHelper, private val roomInfo: RoomInfo) {

//    suspend fun getSeriesInfo() : SeriesInfo {
//        return if (roomInfo.isDatainRoom())
//            //roomInfo.getDataFromRoom()
//        TODO("roomInfo.isDatainRoom()")
//        else {
//            val serie : SeriesInfo = infoHelper.getSeriesInfo()
//            roomInfo.writeToRoom(serie)
//            serie
//        }
//    }

    suspend fun getSeriesInfo() = infoHelper.getSeriesInfo()
}