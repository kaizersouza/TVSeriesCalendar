package com.bychinin.tvseriescalendar.data.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Series::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract val seriesDao: SeriesDao?
}