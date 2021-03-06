package com.bychinin.tvseriescalendar.UI.activities

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bychinin.tvseriescalendar.R
import com.bychinin.tvseriescalendar.UI.Main.MainViewModel
import com.bychinin.tvseriescalendar.UI.adapter.MainAdapter
import com.bychinin.tvseriescalendar.UI.base.ViewModelFactory
import com.bychinin.tvseriescalendar.data.Interfaces.CellClickListener
import com.bychinin.tvseriescalendar.data.api.API.ApiHelper
import com.bychinin.tvseriescalendar.data.api.API.RetrofitBuilder
import com.bychinin.tvseriescalendar.data.api.Cash.CashedHelper
import com.bychinin.tvseriescalendar.data.model.Series.MovieResult
import com.bychinin.tvseriescalendar.data.model.Series.Series
import com.bychinin.tvseriescalendar.databinding.ActivityMainBinding
import com.bychinin.tvseriescalendar.utils.Status
import com.bychinin.tvseriescalendar.utils.Utils

class MainActivity : AppCompatActivity(), CellClickListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter

    private lateinit var days : Pair<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        days = Utils.getWeekDays()
        startWorking(days.first, days.second)

        // prev week
        binding.mainFloatPrev.setOnClickListener {
            days = Utils.minusWeek(days)
            startWorking(days.first, days.second)
        }

        // next week
        binding.mainFloatNext.setOnClickListener {
            days = Utils.plusWeek(days)
            startWorking(days.first, days.second)
        }
    }

    private fun startWorking(air_date_gte: String, air_date_lte: String){

        //
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelper(RetrofitBuilder.apiService),
                CashedHelper(this)
            )
        ).get(MainViewModel::class.java)

        //
        binding.mainRecycleView.layoutManager = LinearLayoutManager(this)
        mainAdapter = MainAdapter(arrayListOf(), this)
        binding.mainRecycleView.adapter = mainAdapter

        // Данные из кеша
        viewModel.getAllFromCash(air_date_gte).observe(this, Observer {
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

        // Данные с сети
        viewModel.getAllFromNet(air_date_gte, air_date_lte).observe(this, Observer {
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
                    Status.LOADING -> {}
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

        Utils.writeLog(text)
    }

    private fun UIshowLoading(){
        // VISIBLE
        binding.ldLoading.visibility = View.VISIBLE
        binding.tvTitle.visibility = View.VISIBLE
        binding.tvNetwork.visibility = View.VISIBLE
        // GONE
        binding.mainRecycleView.visibility = View.GONE
        binding.mainFloatNext.visibility = View.GONE
        binding.mainFloatPrev.visibility = View.GONE
    }

    private fun UIshowError(){
        // VISIBLE
        binding.mainRecycleView.visibility = View.VISIBLE
        // GONE
        binding.ldLoading.visibility = View.GONE
        binding.tvTitle.visibility = View.GONE
        binding.tvNetwork.visibility = View.GONE
        binding.mainFloatNext.visibility = View.GONE
        binding.mainFloatPrev.visibility = View.GONE
    }

    private fun UIshowSuccess(){
        // VISIBLE
        binding.mainRecycleView.visibility = View.VISIBLE
        binding.mainFloatNext.visibility = View.VISIBLE
        binding.mainFloatPrev.visibility = View.VISIBLE
        // GONE
        binding.tvTitle.visibility = View.GONE
        binding.tvNetwork.visibility = View.GONE
        binding.ldLoading.visibility = View.GONE
    }

    //  Запуск активити с подробной информацией о сериале
    override fun onCellClickListener(series: MovieResult) {
            val intent = Intent(this, ViewActivity::class.java)
            intent.putExtra("SERIES_ID", series.id)
            intent.putExtra("VOTE_COUNT", series.vote_count)
            startActivity(intent)
    }

}
