package com.iqbalprabu.weatherapp_mvvm.view.result

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iqbalprabu.weatherapp_mvvm.R
import com.iqbalprabu.weatherapp_mvvm.R.id.weatherList
import com.iqbalprabu.weatherapp_mvvm.model.DailyForecastModel
import kotlinx.android.synthetic.main.fragment_weather_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by Iqbal Prabu Juliantoro
 * on 12/01/19.
 * Github: https://github.com/iqbalprabu
 * Facebook: https://facebook.com/ipj14
 **/

class ResultListFragment: Fragment() {

    private lateinit var weatherResultAdapter: ResultListAdapter

    val TAG = javaClass.simpleName

    val model by sharedViewModel<ResultViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_weather_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //listen Weather list
        model.uiData.observe(this, Observer {
            if(it != null) {
                val weatherList = it.list
                if(weatherList != weatherResultAdapter.list && weatherList.isNotEmpty()) {
                    displayWeather(weatherList)
                } else if(it.error != null) {
                    displayError(it.error)
                }
            }
        })

        weatherResultAdapter = ResultListAdapter(emptyList(), onItemClicked())
        weatherList.layoutManager = LinearLayoutManager(context)
        weatherList.adapter = weatherResultAdapter

        model.getWeatherList()
    }

    private fun onItemClicked(): (DailyForecastModel) -> Unit {
        return { weatherDetail ->
            // notify weather to show
            model.selectedWeatherDetail(weatherDetail.id)
        }
    }

    fun displayWeather(weatherList: List<DailyForecastModel>) {
        weatherResultAdapter.list = weatherList
        weatherResultAdapter.notifyDataSetChanged()
    }

    fun displayError(error: Throwable?) {
        Snackbar.make(weatherList, "Got error : $error", Snackbar.LENGTH_LONG).show()
    }

}