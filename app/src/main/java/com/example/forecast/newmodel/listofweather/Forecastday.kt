package com.example.forecast.newmodel.listofweather


import com.google.gson.annotations.SerializedName

data class Forecastday(
    @SerializedName("astro")
    val astro: Astro? = Astro(),
    @SerializedName("date")
    val date: String? = "",
    @SerializedName("date_epoch")
    val dateEpoch: Int? = 0,
    @SerializedName("day")
    val day: Day? = Day(),
    @SerializedName("hour")
    val hour: List<Hour>? = listOf()
)