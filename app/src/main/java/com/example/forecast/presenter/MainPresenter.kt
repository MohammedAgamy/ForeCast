package com.example.forecast.presenter

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.example.forecast.dataBase.ServiceApis
import com.example.forecast.model.WeatherModelNew
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter {
    var TAG: String = "TAG"
    lateinit var view:MainView
    fun callWeather(call: ServiceApis, location: String) {
        //Toast.makeText(context, location + "this", Toast.LENGTH_SHORT).show()
        call.getService(location).enqueue(object : Callback<WeatherModelNew> {
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