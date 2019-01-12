package com.iqbalprabu.weatherapp_mvvm

import android.app.Application
import com.iqbalprabu.weatherapp_mvvm.di.localAndroidDatasourceModule
import com.iqbalprabu.weatherapp_mvvm.di.weatherApp
import com.joanzapata.iconify.Iconify
import com.joanzapata.iconify.fonts.WeathericonsModule
import org.koin.android.ext.android.startKoin
import org.koin.standalone.StandAloneContext.startKoin


/**
 * Created by Iqbal Prabu Juliantoro
 * on 12/01/19.
 * Github: https://github.com/iqbalprabu
 * Facebook: https://facebook.com/ipj14
 **/

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin context
        startKoin(this, weatherApp + localAndroidDatasourceModule)

        Iconify.with(WeathericonsModule())
    }

}