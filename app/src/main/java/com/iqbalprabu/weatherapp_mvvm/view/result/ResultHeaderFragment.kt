package com.iqbalprabu.weatherapp_mvvm.view.result


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.iqbalprabu.weatherapp_mvvm.R
import com.iqbalprabu.weatherapp_mvvm.util.ext.argument
import com.iqbalprabu.weatherapp_mvvm.view.Arguments.ARG_ADDRESS
import com.iqbalprabu.weatherapp_mvvm.view.Arguments.ARG_WEATHER_DATE
import kotlinx.android.synthetic.main.fragment_result_header.*
import java.util.*

class ResultHeaderFragment : Fragment() {

    val TAG = javaClass.simpleName

    val date: Date by argument(ARG_WEATHER_DATE)
    val address: String by argument(ARG_ADDRESS)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_result_header, container, false) as ViewGroup
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherTitle.text = getString(R.string.weather_title).format(address, date)
    }
}