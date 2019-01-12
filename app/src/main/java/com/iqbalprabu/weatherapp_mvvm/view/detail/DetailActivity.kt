package com.iqbalprabu.weatherapp_mvvm.view.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.iqbalprabu.weatherapp_mvvm.R
import com.iqbalprabu.weatherapp_mvvm.model.DailyForecastModel
import com.iqbalprabu.weatherapp_mvvm.util.ext.argument
import com.iqbalprabu.weatherapp_mvvm.view.Arguments.ARG_ADDRESS
import com.iqbalprabu.weatherapp_mvvm.view.Arguments.ARG_WEATHER_DATE
import com.iqbalprabu.weatherapp_mvvm.view.Arguments.ARG_WEATHER_ITEM_ID
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*

class DetailActivity : AppCompatActivity() {

    // Get all needed data
    private val address by argument<String>(ARG_ADDRESS)
    private val now by argument<Date>(ARG_WEATHER_DATE)
    private val id by argument<String>(ARG_WEATHER_ITEM_ID)

    val detailViewModel by viewModel<DetailViewModel> { parametersOf(id) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailViewModel.uiData.observe(this, android.arch.lifecycle.Observer { detail ->
            if (detail != null) {
                displayDetail(detail)
            }
        })
        detailViewModel.getDetail(id)
    }

    fun displayDetail(weather: DailyForecastModel) {
        weatherTitle.text = getString(R.string.weather_title).format(address, now)
        weatherItemIcon.text = weather.icon
        weatherItemForecast.text = weather.forecastString
        weatherItemTemp.text = weather.temperatureString
    }
}
