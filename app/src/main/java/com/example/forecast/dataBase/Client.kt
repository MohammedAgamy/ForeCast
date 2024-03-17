package com.example.forecast.dataBase

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {

   companion object {
        val BaseUrl: String = "https://api.weatherapi.com/v1/"
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}