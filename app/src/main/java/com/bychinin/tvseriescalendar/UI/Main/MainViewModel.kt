package com.bychinin.tvseriescalendar.UI.Main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bychinin.tvseriescalendar.data.Repository.MainRepository
import com.bychinin.tvseriescalendar.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getAllFromCash(air_date_gte : String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getAllFromCash(air_date_gte)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getAllFromNet(air_date_gte : String, air_date_lte : String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getSeriesFromNet(air_date_gte, air_date_lte)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}