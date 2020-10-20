package com.bychinin.tvseriescalendar.data.api

import android.content.Context
import com.bychinin.tvseriescalendar.data.model.Series.Series


class RoomSeries(
    private val context: Context,
    private val air_date_gte: String,
    private val air_date_lte: String
) {

//    init {
//        val mDatabase = Room.databaseBuilder(
//            context,
//            DataBase::class.java, "series_database"
//                )
//            .fallbackToDestructiveMigration()
//            .build()
//    }

    suspend fun isDatainRoom() : Boolean {
        return false
    }

    suspend fun getDataFromRoom() : Series {
        val series : Series = Series(0, mutableListOf(), 0, 0)

        return series
    }

    suspend fun writeToRoom(series: Series){
    }

}