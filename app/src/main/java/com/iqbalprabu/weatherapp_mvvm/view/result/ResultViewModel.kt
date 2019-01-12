package com.iqbalprabu.weatherapp_mvvm.view.result

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.iqbalprabu.weatherapp_mvvm.model.DailyForecastModel
import com.iqbalprabu.weatherapp_mvvm.repository.WeatherRepository
import com.iqbalprabu.weatherapp_mvvm.util.ext.with
import com.iqbalprabu.weatherapp_mvvm.util.rx.SchedulerProvider
import com.iqbalprabu.weatherapp_mvvm.view.AbstractViewModel
import com.iqbalprabu.weatherapp_mvvm.view.SingleLiveEvent


/**
 * Created by Iqbal Prabu Juliantoro
 * on 12/01/19.
 * Github: https://github.com/iqbalprabu
 * Facebook: https://facebook.com/ipj14
 **/

class ResultViewModel(
    private val weatherRepository: WeatherRepository,
    private val schedulerProvider: SchedulerProvider
): AbstractViewModel() {

    val uiData = MutableLiveData<ResultUIModel>()
    val selectEvent = SingleLiveEvent<ResultSelectEvent>()

    init {
        Log.d(javaClass.name, "ResultViewModel.constructor")
    }

    fun getWeatherList() {

        launch {
            weatherRepository.getWeather()
                .with(schedulerProvider)
                .subscribe({
                    list -> uiData.value = ResultUIModel(list)
                }, {
                    err -> uiData.value = ResultUIModel(error = err)
                })
        }

    }

    fun selectedWeatherDetail(id: String) {
        selectEvent.value = ResultSelectEvent(idSelected = id)
    }

}

data class ResultUIModel(val list: List<DailyForecastModel> = emptyList(), val error: Throwable? = null)
data class ResultSelectEvent(val idSelected: String? = null, val error: Throwable? = null)