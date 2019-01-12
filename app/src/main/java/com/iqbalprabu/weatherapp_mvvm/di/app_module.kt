package com.iqbalprabu.weatherapp_mvvm.di

import com.iqbalprabu.weatherapp_mvvm.repository.WeatherRepository
import com.iqbalprabu.weatherapp_mvvm.repository.WeatherRepositoryImpl
import com.iqbalprabu.weatherapp_mvvm.util.rx.ApplicationSchedulerProvider
import com.iqbalprabu.weatherapp_mvvm.util.rx.SchedulerProvider
import com.iqbalprabu.weatherapp_mvvm.view.detail.DetailViewModel
import com.iqbalprabu.weatherapp_mvvm.view.result.ResultViewModel
import com.iqbalprabu.weatherapp_mvvm.view.search.SearchViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


/**
 * Created by Iqbal Prabu Juliantoro
 * on 12/01/19.
 * Github: https://github.com/iqbalprabu
 * Facebook: https://facebook.com/ipj14
 **/

val weatherModule = module {

    viewModel { SearchViewModel(get(), get()) }

    viewModel { ResultViewModel(get(), get()) }

    viewModel { (id: String) -> DetailViewModel(id, get(), get()) }

    single { WeatherRepositoryImpl(get()) as WeatherRepository}

}

val rxModule = module {
    single { ApplicationSchedulerProvider() as SchedulerProvider }
}

val weatherApp = listOf(weatherModule, rxModule)