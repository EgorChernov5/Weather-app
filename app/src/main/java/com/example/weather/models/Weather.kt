package com.example.weather.models

data class Weather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val weatherList: List<WeatherList>,
    val message: Int
)