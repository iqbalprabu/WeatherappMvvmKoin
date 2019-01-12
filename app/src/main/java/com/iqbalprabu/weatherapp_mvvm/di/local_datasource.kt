package com.iqbalprabu.weatherapp_mvvm.di

import com.iqbalprabu.weatherapp_mvvm.repository.WeatherDatasource
import com.iqbalprabu.weatherapp_mvvm.repository.local.AndroidJsonReader
import com.iqbalprabu.weatherapp_mvvm.repository.local.JsonReader
import com.iqbalprabu.weatherapp_mvvm.repository.local.LocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import kotlin.math.sin


/**
 * Created by Iqbal Prabu Juliantoro
 * on 12/01/19.
 * Github: https://github.com/iqbalprabu
 * Facebook: https://facebook.com/ipj14
 **/

val localAndroidDatasourceModule = module {

    single { AndroidJsonReader(androidApplication()) as JsonReader }
    single { LocalDataSource(get()) as WeatherDatasource }

}