package com.example.forecast.model.modeldays


import com.google.gson.annotations.SerializedName

data class Hour(
    @SerializedName("cloud")
    val cloud: Int? = 0,
    @SerializedName("condition")
    val condition: Condition? = Condition(),
    @SerializedName("dewpoint_c")
    val dewpointC: Double? = 0.0,
    @SerializedName("dewpoint_f")
    val dewpointF: Double? = 0.0,
    @SerializedName("feelslike_c")
    val feelslikeC: Double? = 0.0,
    @SerializedName("feelslike_f")
    val feelslikeF: Double? = 0.0,
    @SerializedName("gust_kph")
    val gustKph: Double? = 0.0,
    @SerializedName("gust_mph")
    val gustMph: Double? = 0.0,
    @SerializedName("heatindex_c")
    val heatindexC: Double? = 0.0,
    @SerializedName("heatindex_f")
    val heatindexF: Double? = 0.0,
    @SerializedName("humidity")
    val humidity: Int? = 0,
    @SerializedName("is_day")
    val isDay: Int? = 0,
    @SerializedName("precip_in")
    val precipIn: Double? = 0.0,
    @SerializedName("precip_mm")
    val precipMm: Double? = 0.0,
    @SerializedName("pressure_in")
    val pressureIn: Double? = 0.0,
    @SerializedName("pressure_mb")
    val pressureMb: Double? = 0.0,
    @SerializedName("sig_ht_mt")
    val sigHtMt: Double? = 0.0,
    @SerializedName("swell_dir")
    val swellDir: Double? = 0.0,
    @SerializedName("swell_dir_16_point")
    val swellDir16Point: String? = "",
    @SerializedName("swell_ht_ft")
    val swellHtFt: Double? = 0.0,
    @SerializedName("swell_ht_mt")
    val swellHtMt: Double? = 0.0,
    @SerializedName("swell_period_secs")
    val swellPeriodSecs: Double? = 0.0,
    @SerializedName("temp_c")
    val tempC: Double? = 0.0,
    @SerializedName("temp_f")
    val tempF: Double? = 0.0,
    @SerializedName("time")
    val time: String? = "",
    @SerializedName("time_epoch")
    val timeEpoch: Int? = 0,
    @SerializedName("uv")
    val uv: Double? = 0.0,
    @SerializedName("vis_km")
    val visKm: Double? = 0.0,
    @SerializedName("vis_miles")
    val visMiles: Double? = 0.0,
    @SerializedName("water_temp_c")
    val waterTempC: Double? = 0.0,
    @SerializedName("water_temp_f")
    val waterTempF: Double? = 0.0,
    @SerializedName("wind_degree")
    val windDegree: Int? = 0,
    @SerializedName("wind_dir")
    val windDir: String? = "",
    @SerializedName("wind_kph")
    val windKph: Double? = 0.0,
    @SerializedName("wind_mph")
    val windMph: Double? = 0.0,
    @SerializedName("windchill_c")
    val windchillC: Double? = 0.0,
    @SerializedName("windchill_f")
    val windchillF: Double? = 0.0
)