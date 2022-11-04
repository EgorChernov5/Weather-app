package com.example.weather.models

data class WeatherDesc(
    val description: String,
    val icon: String,
    val id: Int,
    val mainWeatherGroup: String
)