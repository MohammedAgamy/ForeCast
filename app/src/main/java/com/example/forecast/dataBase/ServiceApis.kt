package com.example.forecast.dataBase

import com.example.forecast.newmodel.WeatherModelNew
import retrofit2.Call
import retrofit2.http.GET

interface ServiceApis {
    @GET("current.json?key=582fd7fa778b489780593251242202&q=29.981022344497138, 31.13160667074973&aqi=no")
    fun getService(): Call<WeatherModelNew>


}