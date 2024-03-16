package com.example.forecast.presenter

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forecast.dataBase.ServiceApis
import com.example.forecast.model.WeatherModelNew
import com.example.forecast.model.modeldays.ListOfWeather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class MainViewModel : ViewModel() {
    var TAG: String = "TAG"
    val call = Repository()
    val data = MutableLiveData<ListOfWeather>()

    fun callWeatherDay(location: String) {
        call.call.getWeatherDay(location).enqueue(object : Callback<ListOfWeather> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(call: Call<ListOfWeather>, response: Response<ListOfWeather>) {
                if (response.isSuccessful) {
                    // get data from api response
                    //val data = response.body()!!
                    data.postValue(response.body())

                } else {
                    Log.i(TAG + "N", "day Not response")
                }
            }

            override fun onFailure(call: Call<ListOfWeather>, t: Throwable) {
                Log.i(TAG + "F", t.message.toString())
            }

        })
    }


}