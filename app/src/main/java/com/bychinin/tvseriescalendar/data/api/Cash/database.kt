package com.bychinin.tvseriescalendar.data.api.Cash

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [(SeriesEntity::class)],version = 2, exportSchema = true)
abstract class AppDb : RoomDatabase() {
    abstract fun seriesDao(): SeriesDAO
}