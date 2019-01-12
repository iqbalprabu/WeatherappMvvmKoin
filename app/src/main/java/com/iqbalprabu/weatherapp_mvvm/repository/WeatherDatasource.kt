package com.iqbalprabu.weatherapp_mvvm.repository

import io.reactivex.Single
import com.iqbalprabu.weatherapp_mvvm.repository.data.geocode.Geocode
import com.iqbalprabu.weatherapp_mvvm.repository.data.weather.Weather
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


/**
 * Created by Iqbal Prabu Juliantoro
 * on 12/01/19.
 * Github: https://github.com/iqbalprabu
 * Facebook: https://facebook.com/ipj14
 **/

/**
 * Weather data souce - Retrofit Tagged
 */

interface WeatherDatasource {

    @GET("/geocode")
    @Headers("Content-type: application/json")
    fun geocode(@Query("address") address: String): Single<Geocode>

    @GET("/weather")
    @Headers("Content-type: application/json")
    fun weather(@Query("lat") lat: Double?, @Query("lon") long: Double?, @Query("lang") lang: String): Single<Weather>


}