package com.iqbalprabu.weatherapp_mvvm.repository.local

import com.iqbalprabu.weatherapp_mvvm.repository.WeatherDatasource
import io.reactivex.Single
import com.iqbalprabu.weatherapp_mvvm.repository.data.geocode.Geocode
import com.iqbalprabu.weatherapp_mvvm.repository.data.geocode.Location
import com.iqbalprabu.weatherapp_mvvm.repository.data.weather.Weather

/**
 * Read json files and render weather data
 */
class LocalDataSource(val jsonReader: JsonReader) : WeatherDatasource {
    private val cities = HashMap<Location, String>()

    init {
        cities += jsonReader.getAllLocations()
    }

    private fun isKnownCity(address: String): Boolean = cities.values.contains(address)

    private fun cityFromLocation(lat: Double?, lng: Double?): String {
        return cities.filterKeys { it.lat == lat && it.lng == lng }.values.firstOrNull()
                ?: DEFAULT_CITY
    }

    override fun geocode(address: String): Single<Geocode> {
        return Single.create { s ->
            val addressToLC = address.toLowerCase()
            val geocode = if (isKnownCity(addressToLC)) {
                jsonReader.getGeocode(addressToLC)
            } else {
                jsonReader.getGeocode(DEFAULT_CITY)
            }
            s.onSuccess(geocode)
        }
    }

    override fun weather(lat: Double?, long: Double?, lang: String): Single<Weather> {
        return Single.create { s ->
            val city = cityFromLocation(lat, long)
            val weather = jsonReader.getWeather(city)
            s.onSuccess(weather)
        }
    }

    companion object {
        const val DEFAULT_CITY = "toulouse"
    }
}