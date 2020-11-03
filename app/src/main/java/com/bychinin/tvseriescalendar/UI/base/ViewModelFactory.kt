package com.bychinin.tvseriescalendar.UI.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bychinin.tvseriescalendar.UI.Main.MainViewModel
import com.bychinin.tvseriescalendar.data.Repository.MainRepository
import com.bychinin.tvseriescalendar.data.api.API.ApiHelper
import com.bychinin.tvseriescalendar.data.api.Cash.CashedHelper

class ViewModelFactory(private val ApiHelper: ApiHelper, private val roomSeries: CashedHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(ApiHelper, roomSeries)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}