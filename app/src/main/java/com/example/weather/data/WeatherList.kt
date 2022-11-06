package com.example.weather.data

data class WeatherList(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: MainInfo,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: List<WeatherDesc>,
    val wind: Wind
)