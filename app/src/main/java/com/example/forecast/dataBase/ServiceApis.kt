package com.example.forecast.dataBase

import com.example.forecast.model.WeatherModelNew
import com.example.forecast.model.modeldays.Hour
import com.example.forecast.model.modeldays.ListOfWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ServiceApis {


    //get weather one day
    @GET("current.json?key=b22316d343eb40479df191601241103")
    fun getService(@Query("q")latLong:String): Call<WeatherModelNew>


    // get weather hour
    @GET("marine.json?key=b22316d343eb40479df191601241103&days=2")
    fun getHour(@Query("q")latLong:String): Call<Hour>

    //get weather 5 day
    @GET("marine.json?key=b22316d343eb40479df191601241103&days=5")
    fun getWeatherDay(@Query("q")latLong:String): Call<ListOfWeather>


}