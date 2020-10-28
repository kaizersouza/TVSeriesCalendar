package com.bychinin.tvseriescalendar.UI.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bychinin.tvseriescalendar.UI.Main.MainViewModel
import com.bychinin.tvseriescalendar.data.Repository.MainRepository
import com.bychinin.tvseriescalendar.data.api.ApiHelper
import com.bychinin.tvseriescalendar.data.api.RoomHelper

class ViewModelFactory(private val ApiHelper: ApiHelper, private val roomSeries: RoomHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(ApiHelper, roomSeries)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}