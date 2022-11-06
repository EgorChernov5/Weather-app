package com.example.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.Resp
import com.example.weather.data.Weather
import com.example.weather.model.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherViewModel(private val weatherService: WeatherService) : ViewModel() {

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = _weather

    private val _resp = MutableLiveData<Resp>()
    val resp: LiveData<Resp> = _resp

    fun loadWeather(city: String) {
        val response = weatherService.getWeather(city)
        response.enqueue( object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                try {
                    if (response.body() == null) throw Exception("Empty response!")
                    _weather.postValue(response.body())
                    _resp.postValue(Resp(response.code(), response.message()))
                } catch (e: Exception) {
                    _resp.postValue(Resp(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                throw Exception("Incorrect request!")
            }
        })
    }
}