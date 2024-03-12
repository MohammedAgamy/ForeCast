package com.example.forecast.model.modeldays


import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday")
    val forecastday: List<Forecastday>? = listOf()
)