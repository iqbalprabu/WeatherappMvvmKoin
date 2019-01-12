package com.iqbalprabu.weatherapp_mvvm.view.result

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.iqbalprabu.weatherapp_mvvm.R
import com.iqbalprabu.weatherapp_mvvm.model.DailyForecastModel
import com.joanzapata.iconify.widget.IconTextView


/**
 * Created by Iqbal Prabu Juliantoro
 * on 12/01/19.
 * Github: https://github.com/iqbalprabu
 * Facebook: https://facebook.com/ipj14
 **/

class ResultListAdapter (
    var list: List<DailyForecastModel>,
    private val onClick: (DailyForecastModel) -> Unit
): RecyclerView.Adapter<ResultListAdapter.WeatherResultholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultListAdapter.WeatherResultholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherResultholder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ResultListAdapter.WeatherResultholder, position: Int) {
        holder.display(list[position], onClick)
    }

    inner class WeatherResultholder(item: View): RecyclerView.ViewHolder(item) {
        private val weatherItemLayout = item.findViewById<LinearLayout>(R.id.weatherItemLayout)
        private val weatherItemForecast = item.findViewById<TextView>(R.id.weatherItemForecast)
        private val weatherItemTemp = item.findViewById<TextView>(R.id.weatherItemTemp)
        private val weatherItemIcon = item.findViewById<IconTextView>(R.id.weatherItemIcon)

        fun display(dailyForecastModel: DailyForecastModel, onClick: (DailyForecastModel) -> Unit) {
            weatherItemLayout.setOnClickListener { onClick(dailyForecastModel) }
            weatherItemForecast.text = dailyForecastModel.forecastString
            weatherItemIcon.text = dailyForecastModel.icon
            weatherItemTemp.text = dailyForecastModel.temperatureString
        }
    }

}