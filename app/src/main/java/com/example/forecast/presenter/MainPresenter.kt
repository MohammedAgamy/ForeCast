package com.example.forecast.presenter

import android.util.Log
import com.example.forecast.model.WeatherModelNew
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter {
    var TAG: String = "TAG"
    lateinit var view: MainView
    val call = Repository()
    fun callWeather(location: String) {
        call.call.getService(location).enqueue(object : Callback<WeatherModelNew> {
            override fun onResponse(call: Call<WeatherModelNew>, response: Response<WeatherModelNew>) {
                if (response.isSuccessful) {

                    view.viewWeatherOneDay(response.body()!!)
                } else {
                    Log.i(TAG, "weather Not response")
                }
            }

            override fun onFailure(call: Call<WeatherModelNew>, t: Throwable) {
                Log.i(TAG, t.message.toString())
            }
        })
    }

}