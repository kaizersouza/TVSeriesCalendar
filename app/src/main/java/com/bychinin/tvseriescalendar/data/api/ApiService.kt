package com.bychinin.tvseriescalendar.data.api

import com.bychinin.tvseriescalendar.data.model.Series
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/tv/?page=1&include_null_first_air_dates=false")
    suspend fun getSeries(@Query("api_key") api_key : String,
                          @Query("with_networks") with_networks : String,
                          @Query("air_date.gte") air_date_gte : String,
                          @Query("air_date.lte") air_date_lte : String
                ): Series

}