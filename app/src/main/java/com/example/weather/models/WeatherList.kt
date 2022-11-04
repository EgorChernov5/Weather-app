package com.example.weather.models

data class WeatherList(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val mainInfo: MainInfo,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weatherDesc: List<WeatherDesc>,
    val wind: Wind
)