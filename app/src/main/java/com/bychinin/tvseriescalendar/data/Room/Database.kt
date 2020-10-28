package com.bychinin.tvseriescalendar.data.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomSeries::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoritesDao() : SeriesDao

}