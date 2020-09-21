package com.bychinin.tvseriescalendar.data.api

class TmdbArray(val id : String, val name : String)

object tmdb {

    // ------------------ API KEY ------------------
    val tmdb_API_KEY : String = "6e4aa48ddb991dd196d34d9ef9b4de73"

    // ------------------ NETWORKS ------------------

    var tmdb_NetworkArray  = arrayOf(
        "213", // Netflix
        "2", // ABC
        "1024", // Amazon
        "2552", // Apple+
        "493", // BBC America
        "16", // CBS
        "2739", // Disney+
        "49", // HBO
        "3186", // HBO Max
        "453", // Hulu
        "3353", // Peacock
        "67", // Showtime
        "318", // Starz
        "3091" // Quibi
    )

}