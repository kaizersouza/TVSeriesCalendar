package com.bychinin.tvseriescalendar.data.api

import android.content.Context
import com.bychinin.tvseriescalendar.data.model.SeriesInfo.SeriesInfo

class RoomInfo(
    private val context: Context,
    private val id : Int
) {

    suspend fun isDatainRoom() : Boolean {
        return false
    }

//    suspend fun getDataFromRoom() : SeriesInfo {
//        val series : SeriesInfo = SeriesInfo(0, mutableListOf(), 0, 0)
//
//        return series
//    }

    suspend fun writeToRoom(serie: SeriesInfo){
    }

}