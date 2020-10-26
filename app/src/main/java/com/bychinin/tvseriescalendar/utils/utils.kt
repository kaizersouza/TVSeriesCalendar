package com.bychinin.tvseriescalendar.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    // Вывод информации в лог
    fun writeLog(any: Any?) {
        val TAG = "0000000000000000000000"
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
        calendarFirst.add(Calendar.DATE, -(dayOfWeek - 2))
        val date1 = sdf.format(calendarFirst.getTime())

        // Последний день недели
        val calendarLast = Calendar.getInstance()
        calendarLast.setTime(sdf.parse(currentDate))
        calendarLast.add(Calendar.DATE, (7 - dayOfWeek + 1))
        val date2 = sdf.format(calendarLast.getTime())

        return Pair<String, String>(date1, date2)
    }

    // Даты предыдущей недели
    fun minusWeek(days: Pair<String, String>) : Pair<String, String> {

        val sdf : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

        val date1 = SimpleDateFormat("yyyy-MM-dd").parse(days.first)
        val newDate1 = Date(date1.getTime() - 604800000L)
        val date11 = sdf.format(newDate1.getTime())

        val date2 = SimpleDateFormat("yyyy-MM-dd").parse(days.second)
        val newDate2 = Date(date2.getTime() - 604800000L)
        val date22 = sdf.format(newDate2.getTime())

        return Pair<String, String>(date11, date22)
    }

    // Даты следующей недели
    fun plusWeek(days: Pair<String, String>) : Pair<String, String> {
        val sdf : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

        val date1 = SimpleDateFormat("yyyy-MM-dd").parse(days.first)
        val newDate1 = Date(date1.getTime() + 604800000L)
        val date11 = sdf.format(newDate1.getTime())

        val date2 = SimpleDateFormat("yyyy-MM-dd").parse(days.second)
        val newDate2 = Date(date2.getTime() + 604800000L)
        val date22 = sdf.format(newDate2.getTime())

        return Pair<String, String>(date11, date22)
    }

}
