package com.example.forecast.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forecast.adapter.WeatherAdapter
import com.example.forecast.dataBase.Client
import com.example.forecast.dataBase.ServiceApis
import com.example.forecast.databinding.FragmentHOMEBinding
import com.example.forecast.newmodel.WeatherModelNew
import com.example.forecast.newmodel.listofweather.Day
import com.example.forecast.newmodel.listofweather.Forecastday
import com.example.forecast.newmodel.listofweather.ListOfWeather

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHOMEBinding
    var TAG: String = "TAG"
    lateinit var mAdapter: WeatherAdapter
    var mList: ArrayList<ListOfWeather> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentHOMEBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var call: ServiceApis = Client.getRetrofit().create(ServiceApis::class.java)
        mAdapter = WeatherAdapter(mList)
        binding.recyclerid.layoutManager = LinearLayoutManager(context)
        binding.recyclerid.adapter = WeatherAdapter(mList)
        callWeather(call)
        callWeatherDay(call)


    }

    fun callWeather(call: ServiceApis) {
        call.getService().enqueue(object : Callback<WeatherModelNew> {
            override fun onResponse(call: Call<WeatherModelNew>, response: Response<WeatherModelNew>) {
                if (response.isSuccessful) {

                    setData(
                        response.body()!!.location!!.country.toString(),
                        response.body()!!.current!!.tempC.toString(),
                        response.body()!!.current!!.cloud.toString(),
                        response.body()!!.current!!.windKph.toString(),
                        response.body()!!.location!!.localtime.toString(),
                        response.body()!!.current!!.condition!!.text.toString()
                    )

                } else {
                    Log.i(TAG, "weather Not response")

                }

            }

            override fun onFailure(call: Call<WeatherModelNew>, t: Throwable) {
                Log.i(TAG, t.message.toString())

            }

        })

    }

    fun callWeatherDay(call: ServiceApis) {
        call.getWeatherDay().enqueue(object : Callback<ListOfWeather> {
            override fun onResponse(call: Call<ListOfWeather>, response: Response<ListOfWeather>) {
                if (response.isSuccessful )  {
                    // Log.i(TAG + "R", response.body()!!.day!!.toString())

                    val data = response.body()!!

                        for (item in data.toString()) {
                                mList.add(data)
                        }

                    mAdapter = WeatherAdapter(mList)
                    binding.recyclerid.layoutManager = LinearLayoutManager(context)
                    binding.recyclerid.adapter = WeatherAdapter(mList)
                    binding.recyclerid.hasFixedSize()



                    //Log.i(TAG + "R", response.body()!!.date!!)


                } else {
                    Log.i(TAG + "N", "day Not response")

                }
            }

            override fun onFailure(call: Call<ListOfWeather>, t: Throwable) {
                Log.i(TAG + "F", t.message.toString())
            }

        })
    }

    fun setData(location: String, temperature: String, cloud: String, Wind: String, time: String, mode: String) {
        binding.location.text = location
        binding.temperature.text = temperature
        binding.cloud.text = "Cloud " + cloud
        binding.Wind.text = "Wind " + Wind
        binding.time.text = time
        binding.mode.text = mode
    }


}


