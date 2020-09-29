package com.bychinin.tvseriescalendar.UI.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bychinin.tvseriescalendar.UI.Main.MainViewModel
import com.bychinin.tvseriescalendar.data.Repository.MainRepository
import com.bychinin.tvseriescalendar.data.api.ApiHelper
import com.bychinin.tvseriescalendar.data.api.RoomSeries

class ViewModelFactory(private val apiHelper: ApiHelper, private val roomSeries: RoomSeries) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper, roomSeries)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}