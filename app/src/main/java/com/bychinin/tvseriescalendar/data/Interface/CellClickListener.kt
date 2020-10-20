package com.bychinin.tvseriescalendar.data.Interface

import com.bychinin.tvseriescalendar.data.model.Series.MovieResult

// Интерфейс для обработки кликов по RecycleView
interface CellClickListener {
    fun onCellClickListener(series : MovieResult)
}