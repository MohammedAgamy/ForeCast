package com.example.forecast.newmodel


import com.google.gson.annotations.SerializedName

data class WeatherModelNew(
    @SerializedName("current")
    val current: Current? = null,
    @SerializedName("location")
    val location: Location? = null
)