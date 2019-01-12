package com.iqbalprabu.weatherapp_mvvm.repository.data

import com.iqbalprabu.weatherapp_mvvm.repository.data.geocode.Geocode
import com.iqbalprabu.weatherapp_mvvm.repository.data.geocode.Location


fun Geocode.getLocation(): Location? = results.firstOrNull()?.geometry?.location