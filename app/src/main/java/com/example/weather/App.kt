package com.example.weather

import android.app.Application
import com.example.weather.models.WeatherService

class App: Application() {
    val weatherService = WeatherService()
}