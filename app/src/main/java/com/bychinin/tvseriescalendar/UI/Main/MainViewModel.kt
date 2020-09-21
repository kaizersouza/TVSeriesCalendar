package com.bychinin.tvseriescalendar.UI.Main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bychinin.tvseriescalendar.data.Repository.MainRepository
import com.bychinin.tvseriescalendar.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getSeries() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getSeries()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}