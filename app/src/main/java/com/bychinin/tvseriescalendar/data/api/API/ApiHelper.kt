package com.bychinin.tvseriescalendar.data.api.API

import com.bychinin.tvseriescalendar.data.api.tmdb
import com.bychinin.tvseriescalendar.data.api.tmdb.tmdb_NetworkArray
import com.bychinin.tvseriescalendar.data.model.Series.MovieResult
import com.bychinin.tvseriescalendar.data.model.Series.Series
import com.bychinin.tvseriescalendar.data.model.movie.Movie
import com.bychinin.tvseriescalendar.utils.Utils

class ApiHelper(private val apiService: ApiService) {

    suspend fun getSeries(air_date_gte : String, air_date_lte : String) : Series {
        val series : Series = Series(0, mutableListOf(), 0, 0)

        tmdb_NetworkArray.forEach {
            val tmp : Series = apiService.getSeries(tmdb.tmdb_API_KEY, it.id, air_date_gte, air_date_lte)
            tmp.results.forEach {
                series.results.add(it)
            }
        }

        return series
    }

    suspend fun getMovies(air_date_gte : String, air_date_lte : String) : Series {
        val series : Series = Series(0, mutableListOf(), 0, 0)
        var page : Int = 1

        val tmp : Movie = apiService.getMovies(tmdb.tmdb_API_KEY, page, air_date_gte, air_date_lte)
        page = tmp?.total_pages ?: 0

        tmp.results?.forEach {
            var movieResult : MovieResult = MovieResult("", "", emptyList(),
                0, "", emptyList(), "", "", "", 0.0,
                "", 0.0, 0)

            movieResult.id = it.id
            movieResult.name = it.title
            movieResult.overview = it.overview
            movieResult.poster_path = it.poster_path
            movieResult.genre_ids = it?.genre_ids ?: emptyList()
            movieResult.vote_count = Utils.VOTE_COUNT

            series.results.add(movieResult)
        }

        if (page > 1) {
            var i : Int = 2
            while (i != page+1) {
                val tmp : Movie = apiService.getMovies(tmdb.tmdb_API_KEY, i, air_date_gte, air_date_lte)

                tmp.results?.forEach {
                    var movieResult : MovieResult = MovieResult("", "", emptyList(),
                        0, "", emptyList(), "", "", "", 0.0,
                        "", 0.0, 0)

                    movieResult.id = it.id
                    movieResult.name = it.title
                    movieResult.overview = it.overview
                    movieResult.poster_path = it.poster_path
                    movieResult.genre_ids = it?.genre_ids ?: emptyList()
                    movieResult.vote_count = Utils.VOTE_COUNT

                    series.results.add(movieResult)
                }
                i ++
            }
        }


        return series
    }

}


