package com.bychinin.tvseriescalendar.data.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bychinin.tvseriescalendar.data.model.Series.Series

@Dao
interface SeriesDao {

    // Добавление сериалов в БД
    @Insert
    fun insertSeries(series: List<RoomSeries>)

    // Удаление всего при выходе
    @Delete
    fun removeSeries()

    // Получение списка сериалов из БД
    @Query("SELECT * from series where air_date_gte=:mAir_date_gte")
    fun getSeries(mAir_date_gte : String):List<Series>

}