package com.bychinin.tvseriescalendar.data.api.Cash

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SeriesDAO
{
    @Insert
    fun saveSeries(series: SeriesEntity)

    @Query(value = "SELECT COUNT(*) from SeriesEntity where air_date_gte=:air_date_gte")
    fun getCountSeriesPerWeek(air_date_gte : String) : Int

    @Query(value = "Select * from SeriesEntity where air_date_gte=:air_date_gte")
    fun getAllPerWeek(air_date_gte : String) : List<SeriesEntity>

    @Query(value = "DELETE FROM SeriesEntity")
    fun deleteAllSeries()

    @Query(value = "DELETE FROM SeriesEntity where air_date_gte=:air_date_gte")
    fun deleteSeriesPerWeek(air_date_gte : String)

}