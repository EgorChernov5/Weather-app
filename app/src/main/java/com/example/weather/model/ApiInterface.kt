package com.example.weather.model

import com.example.weather.data.Weather
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET("data/2.5/forecast")
    fun getWeather(@Query("q") q: String,
                   @Query("cnt") cnt: Int,
                   @Query("units") units: String,
                   @Query("appid") appid: String) : Call<Weather>

    companion object {
        private var BASE_URL = "http://api.openweathermap.org/"
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}