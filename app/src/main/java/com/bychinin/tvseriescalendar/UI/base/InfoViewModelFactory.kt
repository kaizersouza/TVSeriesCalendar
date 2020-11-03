package com.bychinin.tvseriescalendar.UI.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bychinin.tvseriescalendar.UI.Main.InfoViewModel
import com.bychinin.tvseriescalendar.data.Repository.InfoRepository
import com.bychinin.tvseriescalendar.data.api.API.InfoHelper

class InfoViewModelFactory(private val infoHelper: InfoHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfoViewModel::class.java)) {
            return InfoViewModel(InfoRepository(infoHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}