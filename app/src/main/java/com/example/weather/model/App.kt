package com.example.weather.model

import android.app.Application


class App: Application() {
    val weatherService = WeatherService()
}