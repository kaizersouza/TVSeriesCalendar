package com.bychinin.tvseriescalendar.data.model.Series

data class Series(
    val page: Int,
    val results: MutableList<MovieResult>,
    val total_pages: Int,
    var total_results: Int
)

data class MovieResult(
    val backdrop_path: String?,
    val first_air_date: String?,
    var genre_ids: List<Int>,
    var id: Int?,
    var name: String?,
    val origin_country: List<String>,
    val original_language: String?,
    val original_name: String?,
    var overview: String?,
    val popularity: Double?,
    var poster_path: String?,
    val vote_average: Double?,
    var vote_count: Int?
)
