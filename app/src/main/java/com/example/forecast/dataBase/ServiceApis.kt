package com.example.forecast.dataBase

import com.example.forecast.newmodel.WeatherModelNew
import com.example.forecast.newmodel.listofweather.Day
import com.example.forecast.newmodel.listofweather.Forecast
import com.example.forecast.newmodel.listofweather.Forecastday
import com.example.forecast.newmodel.listofweather.ListOfWeather
import com.example.forecast.ui.HomeFragment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ServiceApis {


    @GET("current.json?key=582fd7fa778b489780593251242202&q=28.39358127454683, 35.886640378494974&aqi=no")
    fun getService(): Call<WeatherModelNew>

    @GET("marine.json?key=582fd7fa778b489780593251242202&q=28.39358127454683, 35.886640378494974&days=20")
    fun getWeatherDay():Call<ListOfWeather>


}