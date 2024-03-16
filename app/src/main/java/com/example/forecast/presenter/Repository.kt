package com.example.forecast.presenter

import com.example.forecast.dataBase.Client
import com.example.forecast.dataBase.ServiceApis

class Repository {
    val call: ServiceApis = Client.getRetrofit().create(ServiceApis::class.java)

}