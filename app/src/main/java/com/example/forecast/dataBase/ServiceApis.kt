package com.example.forecast.dataBase

import com.example.forecast.newmodel.WeatherModelNew
import com.example.forecast.newmodel.listofweather.Forecastday
import com.example.forecast.newmodel.listofweather.Hour
import com.example.forecast.newmodel.listofweather.ListOfWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ServiceApis {


    //get weather one day
    @GET("current.json?key=582fd7fa778b489780593251242202")
    fun getService(@Query("q")latLong:String): Call<WeatherModelNew>


    // get weather hour
    @GET("marine.json?key=582fd7fa778b489780593251242202&days=2")
    fun getHour(@Query("q")latLong:String): Call<Hour>

    //get weather 5 day
    @GET("marine.json?key=582fd7fa778b489780593251242202&days=5")
    fun getWeatherDay(@Query("q")latLong:String): Call<ListOfWeather>


}