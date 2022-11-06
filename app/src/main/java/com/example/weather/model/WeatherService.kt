package com.example.weather.model

import com.example.weather.data.Weather
import com.example.weather.API_TOKEN
import retrofit2.Call

class WeatherService {

    private val apiInterface =  ApiInterface.create()

    fun getWeather(city: String): Call<Weather> {
        return apiInterface.getWeather(
            q = city,
            cnt = 40,
            units = "metric",
            appid = API_TOKEN)
    }
}