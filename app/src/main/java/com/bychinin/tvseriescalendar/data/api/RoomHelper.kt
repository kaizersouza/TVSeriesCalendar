package com.bychinin.tvseriescalendar.data.api

import android.app.Application
import androidx.room.Room
import com.bychinin.tvseriescalendar.data.Room.AppDatabase
import com.bychinin.tvseriescalendar.data.model.Series.Series

class RoomHelper : Application() {

//    private lateinit var database : AppDatabase

    override fun onCreate() {
        super.onCreate()
//        database  = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "series_database").build()
    }

    fun isDatainRoom(air_date_gte: String) : Boolean {
//        val series = database.favoritesDao().getSeries(air_date_gte)
//        return series.isEmpty()
        return false
    }

    fun getDataFromRoom(air_date_gte: String) : Series {
        val series : Series = Series(0, mutableListOf(), 0, 0)

        return series
    }

    fun writeToRoom(series: Series){
    }

}