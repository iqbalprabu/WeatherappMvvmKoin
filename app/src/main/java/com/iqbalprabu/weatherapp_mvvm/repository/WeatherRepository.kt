package com.iqbalprabu.weatherapp_mvvm.repository

import com.iqbalprabu.weatherapp_mvvm.model.DailyForecastModel
import com.iqbalprabu.weatherapp_mvvm.repository.data.getDailyForecasts
import com.iqbalprabu.weatherapp_mvvm.repository.data.getLocation
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.IllegalStateException


/**
 * Created by Iqbal Prabu Juliantoro
 * on 12/01/19.
 * Github: https://github.com/iqbalprabu
 * Facebook: https://facebook.com/ipj14
 **/

/**
 * Weather Repository
 **/

interface WeatherRepository {

    fun searchWeather(location: String): Completable
    fun getWeather(): Single<List<DailyForecastModel>>
    fun getSelectedWeatherDetail(id: String): Single<DailyForecastModel>

}

/**
 * Weather Repository
 * Make use of WeatherDatasource & add some cache
 **/

class WeatherRepositoryImpl(private val weatherDatasource: WeatherDatasource): WeatherRepository {

    val weatherCache = arrayListOf<DailyForecastModel>()

    override fun searchWeather(location: String): Completable = weatherDatasource.geocode(location)
        .map { it.getLocation()  ?: throw IllegalStateException("No Location Data") }
        .flatMap { weatherDatasource.weather(it.lat, it.lng, DEFAULT_LANG) }
        .map { it.getDailyForecasts() }
        .doOnSuccess { weatherCache.clear(); weatherCache.addAll(it) }
        .toCompletable()

    override fun getWeather(): Single<List<DailyForecastModel>> = Single.just(weatherCache)

    override fun getSelectedWeatherDetail(id: String): Single<DailyForecastModel> = Single.just(weatherCache.first { it.id == id })

    companion object {
        const val DEFAULT_LANG = "EN"
    }

}