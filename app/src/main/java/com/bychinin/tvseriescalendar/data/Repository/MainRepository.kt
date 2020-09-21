package com.bychinin.tvseriescalendar.data.Repository

import com.bychinin.tvseriescalendar.data.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper) {

    suspend fun getSeries() = apiHelper.getSeries()
}