package com.example.forecast.newmodel.listofweather


import com.google.gson.annotations.SerializedName

data class TideX(
    @SerializedName("tide_height_mt")
    val tideHeightMt: String? = null,
    @SerializedName("tide_time")
    val tideTime: String? = null,
    @SerializedName("tide_type")
    val tideType: String? = null
)