package com.bychinin.tvseriescalendar.UI.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bychinin.tvseriescalendar.R
import com.bychinin.tvseriescalendar.data.model.MovieResult
import kotlinx.android.synthetic.main.item_layout.view.imageViewAvatar
import kotlinx.android.synthetic.main.item_layout.view.textViewUserEmail
import kotlinx.android.synthetic.main.item_layout.view.textViewUserName

class MainAdapter(private val series: ArrayList<MovieResult>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500"

        fun bind(series: MovieResult) {
            itemView.apply {
                textViewUserName.text = series.name
                textViewUserEmail.text = series.overview
                Glide.with(imageViewAvatar.context)
                    .load("${BASE_IMG_URL}${series.poster_path}")
                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = series.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(series[position])
    }

    fun addSeries(series: List<MovieResult>) {
        this.series.apply {
//            clear()
            addAll(series)
        }

    }
}