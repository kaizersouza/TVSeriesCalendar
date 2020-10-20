package com.bychinin.tvseriescalendar.data.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Series {

    @PrimaryKey
    // id запись в таблице
    @ColumnInfo(name = "id")
    private val mId = 0

    // id сериала в API
    @ColumnInfo(name = "id_series")
    private val mIdSeries = 0

    // первая граница диапозона поиска
    @ColumnInfo(name = "air_date_gte")
    private val mAir_date_gte: String? = null

    // URL обложки
    @ColumnInfo(name = "cover")
    private val mCover: String? = null

    // Название
    @ColumnInfo(name = "name")
    private val mName: String? = null

    // Описание
    @ColumnInfo(name = "desc")
    private val mDesc: String? = null

}