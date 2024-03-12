package com.example.forecast.presenter

import com.example.forecast.model.WeatherModelNew

interface MainView {
    fun viewWeatherOneDay(weatherModelNew: WeatherModelNew)
}