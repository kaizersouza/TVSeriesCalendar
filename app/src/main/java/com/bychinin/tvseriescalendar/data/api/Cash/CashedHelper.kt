package com.bychinin.tvseriescalendar.data.api.Cash

import android.content.Context
import androidx.room.Room
import com.bychinin.tvseriescalendar.data.model.Series.MovieResult
import com.bychinin.tvseriescalendar.data.model.Series.Series
import com.bychinin.tvseriescalendar.utils.Utils

class CashedHelper(val context: Context) {

    lateinit var db : AppDb

    init {
        db = Room
            .databaseBuilder(context, AppDb::class.java,"SeriesDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    // Получить сериалы за неделю из кеша
    suspend fun getAllFromCash(air_date_gte: String) : Series {
        val results: MutableList<MovieResult> = mutableListOf()
        db.seriesDao().getAllPerWeek(air_date_gte).forEach {
            val ser : MovieResult = MovieResult("", "", emptyList(), 0,
                "", emptyList(), "", "", "", 0.0,
            "", 0.0, 0)
            ser.id = it.seriesID
            ser.name = it.seriesName
            ser.overview = it.overview
            ser.poster_path = it.posterPath
            results.add(ser)
        }
        return Series(0, results, 0, 0)
    }

    // Записать сериалы за неделю в кеш
    suspend fun writeAllToCash(series: Series, air_date_gte : String){
        series.results.forEach {
            val seriesEntity = SeriesEntity()
            seriesEntity.air_date_gte = air_date_gte
            seriesEntity.seriesName = it.name ?: ""
            seriesEntity.overview = it.overview ?: ""
            seriesEntity.posterPath = it.poster_path ?: ""
            seriesEntity.seriesID = it.id ?: 0
            db.seriesDao().saveSeries(seriesEntity)
        }
    }

}