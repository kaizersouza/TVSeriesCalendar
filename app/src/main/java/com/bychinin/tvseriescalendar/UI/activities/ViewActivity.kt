package com.bychinin.tvseriescalendar.UI.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bychinin.tvseriescalendar.R
import com.bychinin.tvseriescalendar.UI.Main.InfoViewModel
import com.bychinin.tvseriescalendar.UI.base.InfoViewModelFactory
import com.bychinin.tvseriescalendar.data.api.InfoHelper
import com.bychinin.tvseriescalendar.data.api.RoomInfo
import com.bychinin.tvseriescalendar.data.model.SeriesInfo.SeriesInfo
import com.bychinin.tvseriescalendar.databinding.ActivityViewBinding
import com.bychinin.tvseriescalendar.utils.Status

class ViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBinding
    private lateinit var viewInfoModel: InfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id : Int = intent.getIntExtra("SERIES_ID", 0)
        startWorking(id)

        binding.viewIvFinish.setOnClickListener {
            finish()
        }

    }

    private fun startWorking(id: Int){

        //
        viewInfoModel = ViewModelProviders.of(
            this,
            InfoViewModelFactory(InfoHelper(id), RoomInfo(this, id))
        ).get(InfoViewModel::class.java)

        //
        setupObservers()
    }

    private fun setupObservers() {
        viewInfoModel.getSeriesInfo().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        UIshowSuccess()
                        resource.data?.let { serieInfo -> retrieveList(serieInfo) }
                    }
                    Status.ERROR -> {
                        UIshowError()
                        showErrorToast(it.message)
                    }
                    Status.LOADING -> {
                        UIshowLoading()
                    }
                }
            }
        })
    }

    private fun retrieveList(serieInfo: SeriesInfo) {

        val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500"

        // Text Fields
        binding.viewTvName.text = serieInfo.name
        binding.viewTvDesc.text = serieInfo.overview
        binding.viewTvRating.text = "${resources.getString(R.string.vote)} ${serieInfo.vote_average}"
        binding.viewTvSeasonsCount.text = "Seasons: ${serieInfo.number_of_seasons}\nEpisodes: ${serieInfo.number_of_episodes}"

        // Poster
        Glide.with(this)
            .load("${BASE_IMG_URL}${serieInfo.poster_path}")
            .error(R.drawable.ic_error_loading)
            .centerCrop()
            .into(binding.viewIvPoster)

        // genres
        var genres : String = ""
        if (serieInfo.genres!!.isNotEmpty()) {
            for (g in serieInfo.genres) {
                if (genres == "") {
                    genres += "${g.name}"
                } else {
                    genres += ", \n${g.name}"
                }
            }
        }
        binding.viewTvGenres.text = "${genres}"

        // networks
        val layoutInflater = LayoutInflater.from(this)
        for (n in serieInfo.networks) {
            val view: View = layoutInflater.inflate(
                R.layout.network_layout,
                binding.linearnetwork,
                false
            )
            val imageView1: ImageView = view.findViewById(R.id.iv_network_logo)
            Glide.with(this)
                .load("${BASE_IMG_URL}${n.logo_path}")
                .error(R.drawable.ic_error_loading)
                .into(imageView1)
            binding.linearnetwork.addView(view)
        }

        binding.linearnetwork.setOnClickListener {
            try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(serieInfo.homepage)))
                }
            catch (e: ActivityNotFoundException) {}
        }

        // seasons
        val linearLayout1 : LinearLayout = findViewById(R.id.linearseasons)
        for (i in 0 until serieInfo.seasons.size) {
            val view: View = layoutInflater.inflate(R.layout.seasons_layout, linearLayout1, false)
            val imageView = view.findViewById<ImageView>(R.id.iv_season_logo)
            Glide.with(this)
                    .load("${BASE_IMG_URL}${serieInfo.seasons[i].poster_path}")
                    .error(R.drawable.ic_error_loading)
                    .into(imageView)
            val tv = view.findViewById<TextView>(R.id.tv_season_name)
            tv.setText(serieInfo.seasons[i].name)
            linearLayout1.addView(view)
        }

    }

    private fun showErrorToast(txt: String?){
        val text : String = txt ?: resources.getString(R.string.app_error)
        val inflater : LayoutInflater = layoutInflater
        val layout : View = inflater.inflate(R.layout.toast_error_layout, null)
        val textView : TextView = layout.findViewById(R.id.toast_txt)
        textView.setText(text)
        val toast : Toast = Toast(this)
        toast.view = layout
        toast.show()
    }

    private fun UIshowError() {
        // VISIBLE
        binding.viewIvFinish.visibility = View.VISIBLE
        // INVISIBLE
        binding.viewLdLoading.visibility = View.INVISIBLE
        binding.viewTvName.visibility = View.INVISIBLE
        binding.viewIvPoster.visibility = View.INVISIBLE
        binding.viewTvDesc.visibility = View.INVISIBLE
        binding.viewIvFinish.visibility = View.INVISIBLE
        binding.viewTvGenres.visibility = View.INVISIBLE
    }

    private fun UIshowLoading() {
        // VISIBLE
        binding.viewLdLoading.visibility = View.VISIBLE
        // INVISIBLE
        binding.viewIvFinish.visibility = View.INVISIBLE
        binding.viewTvName.visibility = View.INVISIBLE
        binding.viewIvPoster.visibility = View.INVISIBLE
        binding.viewTvDesc.visibility = View.INVISIBLE
        binding.viewIvFinish.visibility = View.INVISIBLE
        binding.viewTvGenres.visibility = View.INVISIBLE
    }

    private fun UIshowSuccess() {
        // INVISIBLE
        binding.viewLdLoading.visibility = View.INVISIBLE
        // VISIBLE
        binding.viewIvFinish.visibility = View.VISIBLE
        binding.viewTvName.visibility = View.VISIBLE
        binding.viewIvPoster.visibility = View.VISIBLE
        binding.viewTvDesc.visibility = View.VISIBLE
        binding.viewIvFinish.visibility = View.VISIBLE
        binding.viewTvGenres.visibility = View.VISIBLE
    }

}

