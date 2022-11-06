package com.example.weather.model

import android.app.Application
import com.example.weather.model.WeatherService

class App: Application() {
    val weatherService = WeatherService()
}