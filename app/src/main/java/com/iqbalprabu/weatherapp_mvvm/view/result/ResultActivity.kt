package com.iqbalprabu.weatherapp_mvvm.view.result

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.iqbalprabu.weatherapp_mvvm.R
import com.iqbalprabu.weatherapp_mvvm.util.ext.argument
import com.iqbalprabu.weatherapp_mvvm.util.ext.withArguments
import com.iqbalprabu.weatherapp_mvvm.view.Arguments.ARG_ADDRESS
import com.iqbalprabu.weatherapp_mvvm.view.Arguments.ARG_WEATHER_DATE
import com.iqbalprabu.weatherapp_mvvm.view.Arguments.ARG_WEATHER_ITEM_ID
import com.iqbalprabu.weatherapp_mvvm.view.detail.DetailActivity
import org.jetbrains.anko.startActivity
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class ResultActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    val myModel by viewModel<ResultViewModel>()

    val date: Date by argument(ARG_WEATHER_DATE)
    val address: String by argument(ARG_ADDRESS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        myModel.selectEvent.observe(this, android.arch.lifecycle.Observer {selectEvent ->
            if(selectEvent != null) {
                if(selectEvent.idSelected != null) {
                    onWeatherSelected(selectEvent.idSelected)
                } else if(selectEvent.error != null) {
                    displayError(selectEvent.error)
                }
            }
        })

        // Launch fragments
        val weatherResultTitleFragment = ResultHeaderFragment()
            .withArguments(ARG_WEATHER_DATE to date,
                ARG_ADDRESS to address)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.weather_title, weatherResultTitleFragment)
            .commit()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.weather_list, ResultListFragment())
            .commit()
    }

    fun onWeatherSelected(id: String) {
        Toast.makeText(this, "onWeatherSelected.id: " + id, Toast.LENGTH_LONG)
        startActivity<DetailActivity>(
            ARG_WEATHER_DATE to date,
            ARG_ADDRESS to address,
            ARG_WEATHER_ITEM_ID to id)
    }

    fun displayError(error: Throwable?) {
        Snackbar.make(currentFocus, "Got error : $error", Snackbar.LENGTH_LONG).show()
    }
}
