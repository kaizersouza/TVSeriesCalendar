package com.bychinin.tvseriescalendar.UI.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bychinin.tvseriescalendar.R
import com.bychinin.tvseriescalendar.data.Interfaces.CellClickListener
import com.bychinin.tvseriescalendar.data.model.Series.MovieResult
import com.bychinin.tvseriescalendar.utils.Utils
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(private val series: ArrayList<MovieResult>, private val cellClickListener: CellClickListener) : RecyclerView.Adapter<MainAdapter.DataViewHolder>(){

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500"

        fun bind(series: MovieResult) {
            itemView.apply {
                main_tv_name.text = series.name
                main_tv_desc.text = series.overview
                if (series.vote_count == Utils.VOTE_COUNT) {
                    main_tv_type.text = "Movie"
                } else {main_tv_type.text = "TV Series"}
                Glide.with(imageViewAvatar.context)
                    .load("${BASE_IMG_URL}${series.poster_path}")
                    .error(R.drawable.ic_error_loading)
                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = series.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(series[position])

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(series[position])
        }
    }

    fun addSeries(series: List<MovieResult>) {
        this.series.apply {
            clear()
            addAll(series)
        }

    }
}
