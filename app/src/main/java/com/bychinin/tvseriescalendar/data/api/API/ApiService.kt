package com.bychinin.tvseriescalendar.data.api.API

import com.bychinin.tvseriescalendar.data.model.Series.Series
import com.bychinin.tvseriescalendar.data.model.SeriesInfo.SeriesInfo
import com.bychinin.tvseriescalendar.data.model.movie.Movie
import com.bychinin.tvseriescalendar.data.model.movie.MoviesInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/tv/?page=1&include_null_first_air_dates=false")
    suspend fun getSeries(@Query("api_key") api_key : String,
                          @Query("with_networks") with_networks : String,
                          @Query("air_date.gte") air_date_gte : String,
                          @Query("air_date.lte") air_date_lte : String
                ): Series

    @GET("tv/{id}")
    suspend fun getSeriesInfo(@Path("id") id : Int,
                              @Query("api_key") api_key : String
    ): SeriesInfo

    @GET("discover/movie/?include_null_first_air_dates=false")
    suspend fun getMovies(@Query("api_key") api_key : String,
                          @Query("page") page : Int,
                          @Query("primary_release_date.gte") primary_release_date_gte : String,
                          @Query("primary_release_date.lte") primary_release_date_lte : String
    ): Movie

    @GET("movie/{id}")
    suspend fun getMoviesInfo(@Path("id") id : Int,
                              @Query("api_key") api_key : String
    ): MoviesInfo

}