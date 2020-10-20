package com.bychinin.tvseriescalendar.UI.Main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bychinin.tvseriescalendar.data.Repository.InfoRepository
import com.bychinin.tvseriescalendar.utils.Resource
import kotlinx.coroutines.Dispatchers

class InfoViewModel(private val viewRepository: InfoRepository) : ViewModel() {

    fun getSeriesInfo() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = viewRepository.getSeriesInfo()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}