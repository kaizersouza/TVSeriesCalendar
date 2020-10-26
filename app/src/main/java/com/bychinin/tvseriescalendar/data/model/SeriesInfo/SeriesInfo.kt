package com.bychinin.tvseriescalendar.data.model.SeriesInfo

data class SeriesInfo(
    val backdrop_path: String?,
    val created_by: List<CreatedBy>,
    val episode_run_time: List<Any>?,
    val first_air_date: String?,
    val genres: List<Genre>?,
    val homepage: String?,
    val id: Int?,
    val in_production: Boolean?,
    val languages: List<String>?,
    val last_air_date: Any?,
    val last_episode_to_air: Any?,
    val name: String?,
    val networks: List<Network>,
    val next_episode_to_air: NextEpisodeToAir?,
    val number_of_episodes: Int?,
    val number_of_seasons: Int?,
    val origin_country: List<Any>?,
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val production_companies: List<Any>?,
    val seasons: List<Season>,
    val status: String?,
    val type: String?,
    val vote_average: Double,
    val vote_count: Int
)