package com.example.weather.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.models.Weather
import com.example.weather.models.WeatherService

class WeatherViewModel(private val weatherService: WeatherService) : ViewModel() {

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = _weather

    init {
        loadWeather()
    }

    fun loadWeather() {
        _weather.value = weatherService.getWeather()
    }
}