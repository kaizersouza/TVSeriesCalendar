package com.bychinin.tvseriescalendar.data.api

import android.content.Context
import android.content.Intent
import com.bychinin.tvseriescalendar.data.api.tmdb.tmdb_NetworkArray
import com.bychinin.tvseriescalendar.data.model.Series.Series
import com.bychinin.tvseriescalendar.utils.Utils

class ApiHelper(
    private val cont : Context,
    private val apiService: ApiService
) {

    fun sendNetwork(networkName : String){
        val intentTopUI = Intent(tmdb.NETWORK_NETWORK_ACTION)
        intentTopUI.putExtra(tmdb.NETWORK_NETWORK_ACTION_NAME, networkName)
        cont.sendBroadcast(intentTopUI)
    }

    suspend fun getSeries(air_date_gte : String, air_date_lte : String) : Series {
        val series : Series = Series(0, mutableListOf(), 0, 0)

        Utils.writeLog("API Helper $air_date_gte $air_date_lte")

        tmdb_NetworkArray.forEach {
            sendNetwork(it.name)
            val tmp : Series = apiService.getSeries(tmdb.tmdb_API_KEY, it.id, air_date_gte, air_date_lte)
            tmp.results.forEach {
                series.results.add(it)
            }
        }

        return series
    }

}


