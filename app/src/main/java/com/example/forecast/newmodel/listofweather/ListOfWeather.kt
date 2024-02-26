package com.example.forecast.newmodel.listofweather


import com.google.gson.annotations.SerializedName

data class ListOfWeather(
    @SerializedName("forecast")
    val forecast: Forecast? = Forecast(),
    @SerializedName("location")
    val location: Location? = Location()
)