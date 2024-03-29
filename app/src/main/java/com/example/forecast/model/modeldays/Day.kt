package com.example.forecast.model.modeldays


import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("avghumidity")
    val avghumidity: Double? = 0.0,
    @SerializedName("avgtemp_c")
    val avgtempC: Double? = 0.0,
    @SerializedName("avgtemp_f")
    val avgtempF: Double? = 0.0,
    @SerializedName("avgvis_km")
    val avgvisKm: Double? = 0.0,
    @SerializedName("avgvis_miles")
    val avgvisMiles: Double? = 0.0,
    @SerializedName("condition")
    val condition: Condition? = Condition(),
    @SerializedName("maxtemp_c")
    val maxtempC: Double? = 0.0,
    @SerializedName("maxtemp_f")
    val maxtempF: Double? = 0.0,
    @SerializedName("maxwind_kph")
    val maxwindKph: Double? = 0.0,
    @SerializedName("maxwind_mph")
    val maxwindMph: Double? = 0.0,
    @SerializedName("mintemp_c")
    val mintempC: Double? = 0.0,
    @SerializedName("mintemp_f")
    val mintempF: Double? = 0.0,
    @SerializedName("tides")
    val tides: List<Tide>? = listOf(),
    @SerializedName("totalprecip_in")
    val totalprecipIn: Double? = 0.0,
    @SerializedName("totalprecip_mm")
    val totalprecipMm: Double? = 0.0,
    @SerializedName("totalsnow_cm")
    val totalsnowCm: Double? = 0.0,
    @SerializedName("uv")
    val uv: Double? = 0.0
)