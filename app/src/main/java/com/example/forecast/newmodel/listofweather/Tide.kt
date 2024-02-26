package com.example.forecast.newmodel.listofweather


import com.google.gson.annotations.SerializedName

data class Tide(
    @SerializedName("tide")
    val tide: List<TideX>? = listOf()
)