package com.bychinin.tvseriescalendar.data.Interfaces

import com.bychinin.tvseriescalendar.data.model.Series.MovieResult

// Интерфейс для обработки кликов по RecycleView
interface CellClickListener {
    fun onCellClickListener(series : MovieResult)
}