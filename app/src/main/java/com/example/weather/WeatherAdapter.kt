package com.example.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.databinding.ListItemBinding
import com.example.weather.models.WeatherList

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    var weatherList: List<WeatherList> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = weatherList[position]
        with(holder.binding) {
            weatherGroup.text = weather.weatherDesc[0].mainWeatherGroup
            weatherGroupDesc.text = weather.weatherDesc[0].description
            temp.text = weather.mainInfo.temp.toString() + " C"
            date.text = weather.dt_txt
            if (weather.weatherDesc[0].icon.isNotEmpty()) {
                Glide.with(iconWeather.context)
                    .load(weather.weatherDesc[0].icon)
                    .placeholder(R.drawable.ic_weather_exclude)
                    .error(R.drawable.ic_weather_exclude)
                    .into(iconWeather)
            } else {
                iconWeather.setImageResource(R.drawable.ic_weather_exclude)
            }
        }
    }

    override fun getItemCount(): Int = weatherList.size

    class WeatherViewHolder(
        val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}