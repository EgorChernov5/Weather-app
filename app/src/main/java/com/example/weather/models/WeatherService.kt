package com.example.weather.models

class WeatherService() {
    private lateinit var weather: Weather

    init {
        // todo initial data
    }

    fun getWeather(): Weather {
        return weather
    }
}