package com.bychinin.tvseriescalendar.data.api.Cash

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SeriesEntity {

    @PrimaryKey(autoGenerate = true) var Id: Int =0
    @ColumnInfo (name ="name") var seriesName:  String = ""
    @ColumnInfo (name ="air_date_gte") var air_date_gte:  String = ""
    @ColumnInfo (name ="overview") var overview:  String = ""
    @ColumnInfo (name ="poster_path") var posterPath:  String = ""
    @ColumnInfo (name ="seriesID") var seriesID:  Int = 0

}