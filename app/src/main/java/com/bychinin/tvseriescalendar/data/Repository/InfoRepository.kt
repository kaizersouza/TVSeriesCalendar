package com.bychinin.tvseriescalendar.data.Repository

import com.bychinin.tvseriescalendar.data.api.API.InfoHelper

class InfoRepository (private val infoHelper: InfoHelper) {

    suspend fun getAllInfo(voute_count : Int) = infoHelper.getSeriesInfo(voute_count)
}