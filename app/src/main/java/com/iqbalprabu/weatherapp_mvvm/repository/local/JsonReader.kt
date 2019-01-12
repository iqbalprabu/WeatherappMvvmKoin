package com.iqbalprabu.weatherapp_mvvm.repository.local

import com.iqbalprabu.weatherapp_mvvm.repository.data.geocode.Geocode
import com.iqbalprabu.weatherapp_mvvm.repository.data.geocode.Location
import com.iqbalprabu.weatherapp_mvvm.repository.data.weather.Weather

/**
 * Json reader
 */
interface JsonReader {
    fun getAllLocations(): Map<Location, String>
    fun getWeather(name: String): Weather
    fun getGeocode(name: String): Geocode
}