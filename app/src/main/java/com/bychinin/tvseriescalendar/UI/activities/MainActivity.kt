package com.bychinin.tvseriescalendar.UI.activities

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bychinin.tvseriescalendar.R
import com.bychinin.tvseriescalendar.UI.Main.MainViewModel
import com.bychinin.tvseriescalendar.UI.adapter.MainAdapter
import com.bychinin.tvseriescalendar.UI.base.ViewModelFactory
import com.bychinin.tvseriescalendar.UI.view.Loady
import com.bychinin.tvseriescalendar.data.api.ApiHelper
import com.bychinin.tvseriescalendar.data.api.RetrofitBuilder
import com.bychinin.tvseriescalendar.data.api.RoomSeries
import com.bychinin.tvseriescalendar.data.model.Series
import com.bychinin.tvseriescalendar.utils.Status
import com.bychinin.tvseriescalendar.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    private lateinit var viewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val days : Pair<String, String> = Utils.getWeekDays()
        startWorking(days.first, days.second)

    }

    private fun startWorking(air_date_gte: String, air_date_lte: String){

        //
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelper(RetrofitBuilder.apiService, air_date_gte, air_date_lte), RoomSeries(
                    air_date_gte,
                    air_date_lte
                )
            )
        ).get(MainViewModel::class.java)

        //
        main_recycleView.layoutManager = LinearLayoutManager(this)
        mainAdapter = MainAdapter(arrayListOf())
        main_recycleView.addItemDecoration(
            DividerItemDecoration(
                main_recycleView.context,
                (main_recycleView.layoutManager as LinearLayoutManager).orientation
            )
        )
        main_recycleView.adapter = mainAdapter

        //
        setupObservers()
    }


    private fun setupObservers() {
        viewModel.getSeries().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        UIshowSuccess()
                        resource.data?.let { series -> retrieveList(series) }
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

    private fun retrieveList(series: Series) {
        mainAdapter.apply {
            addSeries(series.results)
            notifyDataSetChanged()
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

    private fun UIshowLoading(){
        ld_loading.visibility = View.VISIBLE
        tv_title.visibility = View.VISIBLE
        tv_network.visibility = View.VISIBLE
        main_recycleView.visibility = View.GONE
    }

    private fun UIshowError(){
        main_recycleView.visibility = View.VISIBLE
        ld_loading.visibility = View.GONE
        tv_title.visibility = View.GONE
        tv_network.visibility = View.GONE
    }

    private fun UIshowSuccess(){
        main_recycleView.visibility = View.VISIBLE
        tv_title.visibility = View.GONE
        tv_network.visibility = View.GONE
        ld_loading.visibility = View.GONE
    }
}
