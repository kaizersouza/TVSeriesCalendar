package com.bychinin.tvseriescalendar.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    val TAG = "0000000000000000000000"

    // Вывод информации в лог
    fun writeLog(any: Any?) {
        Log.d(TAG, any.toString())
    }

    // Получение первого и последнего дня текущенй недели
    fun getWeekDays() : Pair<String, String> {

        val sdf : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

        // Номер дня недели
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        var dayOfWeek = calendar[Calendar.DAY_OF_WEEK]

        val currentDate = sdf.format(Date())

        // Первый день недели
        val calendarFirst = Calendar.getInstance()
        calendarFirst.setTime(sdf.parse(currentDate))
        calendarFirst.add(Calendar.DATE, - (dayOfWeek - 2))
        val date1 = sdf.format(calendarFirst.getTime())

        // Последний день недели
        val calendarLast = Calendar.getInstance()
        calendarLast.setTime(sdf.parse(currentDate))
        calendarLast.add(Calendar.DATE,  (7 - dayOfWeek + 1))
        val date2 = sdf.format(calendarLast.getTime())

        return Pair<String, String>(date1, date2)
    }

}
