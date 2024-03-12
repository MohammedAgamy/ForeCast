package com.example.forecast.model.modeldays


import com.google.gson.annotations.SerializedName

data class Tide(
    @SerializedName("tide")
    val tide: List<TideX>? = listOf()
)