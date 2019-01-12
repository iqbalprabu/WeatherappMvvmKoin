package com.iqbalprabu.weatherapp_mvvm.view.detail

import android.arch.lifecycle.MutableLiveData
import com.iqbalprabu.weatherapp_mvvm.model.DailyForecastModel
import com.iqbalprabu.weatherapp_mvvm.repository.WeatherRepository
import com.iqbalprabu.weatherapp_mvvm.util.ext.with
import com.iqbalprabu.weatherapp_mvvm.util.rx.SchedulerProvider
import com.iqbalprabu.weatherapp_mvvm.view.AbstractViewModel


/**
 * Created by Iqbal Prabu Juliantoro
 * on 12/01/19.
 * Github: https://github.com/iqbalprabu
 * Facebook: https://facebook.com/ipj14
 **/

class DetailViewModel(
    id: String,
    private val weatherRepository: WeatherRepository,
    private val schedulerProvider: SchedulerProvider
): AbstractViewModel() {

    val uiData = MutableLiveData<DailyForecastModel>()

    fun getDetail(id: String) {
        launch {
            weatherRepository.getSelectedWeatherDetail(id)
                .with(schedulerProvider)
                .subscribe(
                    { d -> uiData.value = d},
                    { e -> }
                )
        }
    }

}