package com.bychinin.tvseriescalendar.data.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SeriesDao {

    // Добавление сериалов в БД
    @Insert
    fun insertSeries(series: List<Series>)

    // Получение списка сериалов из БД
    @Query("SELECT * from series where air_date_gte=air_date_gte")
    fun getSeries(mAir_date_gte : String):List<Series>

}