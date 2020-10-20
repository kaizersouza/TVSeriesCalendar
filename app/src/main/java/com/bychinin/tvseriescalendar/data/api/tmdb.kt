package com.bychinin.tvseriescalendar.data.api

class TmdbArray(val id : String, val name : String)

object tmdb {

    // ------------------ API KEY ------------------
    val tmdb_API_KEY : String = "6e4aa48ddb991dd196d34d9ef9b4de73"

    // ------------------ NETWORKS ------------------

    class TMDBClass(val id : String, val name : String)

    var tmdb_NetworkArray : List<TMDBClass> = listOf(
        TMDBClass("213", "Netflix"),
        TMDBClass("2", "ABC"),
        TMDBClass("1024", "Amazon"),
        TMDBClass("2552", "Apple+"),
        TMDBClass("493", "BBC America"),
        TMDBClass("16", "CBS"),
        TMDBClass("2739", "Disney+"),
        TMDBClass("49", "HBO"),
        TMDBClass("3186", "HBO Max"),
        TMDBClass("453", "Hulu"),
        TMDBClass("3353", "Peacock"),
        TMDBClass("67", "Showtime"),
        TMDBClass("318", "Starz"),
        TMDBClass("3091", "Quibi"),
        TMDBClass("88", "FX"),
        TMDBClass("174", "AMC")
    )


    // ------------------ BROADCAST SIGNAL ------------------

    val NETWORK_NETWORK_ACTION_NAME : String = "NETWORK_NETWORK_ACTION_NAME"
    val NETWORK_NETWORK_ACTION : String = "com.bychinin.tvseriescalendar.NETWORK_NETWORK_ACTION"

}