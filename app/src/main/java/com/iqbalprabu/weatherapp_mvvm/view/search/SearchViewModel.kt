package com.iqbalprabu.weatherapp_mvvm.view.search

import android.arch.lifecycle.MutableLiveData
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

class SearchViewModel (
    private val weatherRepository: WeatherRepository,
    private val schedulerProvider: SchedulerProvider
): AbstractViewModel() {

    val searchEvent = SingleLiveEvent<SearchEvent>()
    val uiData = MutableLiveData<SearchUIModel>()

    fun searchWeather(address: String) {
        launch {
            uiData.value = SearchUIModel(address)
            searchEvent.value = SearchEvent(isLoading = true)

            weatherRepository.searchWeather(address)
                        .with(schedulerProvider)
                        .subscribe (
                            {
                                searchEvent.postValue(SearchEvent(isSuccess = true))
                            },
                            {
                                searchEvent.postValue(SearchEvent(error = it))
                            }
                        )
        }
    }

}

data class SearchUIModel(val searchText: String? = null)
data class SearchEvent(val isLoading: Boolean = false, val isSuccess: Boolean = false, val error: Throwable? = null)