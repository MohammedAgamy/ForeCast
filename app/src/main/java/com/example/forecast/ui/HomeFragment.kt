package com.example.forecast.ui

import android.annotation.SuppressLint

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.forecast.adapter.WeatherAdapter
import com.example.forecast.adapter.WeatherHourAdapter
import com.example.forecast.dataBase.Client
import com.example.forecast.dataBase.ServiceApis
import com.example.forecast.databinding.FragmentHOMEBinding
import com.example.forecast.model.WeatherModelNew
import com.example.forecast.model.modeldays.Hour
import com.example.forecast.model.modeldays.ListOfWeather
import com.example.forecast.presenter.MainPresenter
import com.example.forecast.presenter.MainView
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("CAST_NEVER_SUCCEEDS")
class HomeFragment : Fragment() ,MainView {

    lateinit var binding: FragmentHOMEBinding
    var TAG: String = "TAG"
    lateinit var mAdapter: WeatherAdapter
    lateinit var hourAdapter: WeatherHourAdapter
    var mList: ArrayList<ListOfWeather> = ArrayList()
    var mListHour: ArrayList<Hour> = ArrayList()

    //lat and long location
    var lat: String? = null
    var long: String? = null

    var presenter = MainPresenter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHOMEBinding.inflate(inflater, container, false)
        presenter.view =this
        getLocation()
        return binding.getRoot();

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get current location lat and long
        getLocation()
        binding.btnBack.setOnClickListener {
            System.exit(-1)
        }
    }


    // response from api (all day)
    fun callWeatherDay(call: ServiceApis, location: String) {
       // Log.i(TAG + "day", "$lat, $long")

        call.getWeatherDay(location).enqueue(object : Callback<ListOfWeather> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<ListOfWeather>, response: Response<ListOfWeather>,
            ) {
                if (response.isSuccessful) {
                    // get data from api response
                    val data = response.body()!!
                    dataOfDays(data)
                    //dataOfHour(data)

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




    // get weather data to all day
    fun dataOfDays(data: ListOfWeather) {
        //get 5 item
        for (item in mList.size.until(5)) {

            if (mList.toString().length >= 5)
            {
                break
            }
            else{
                mList.add(data)
                mAdapter = WeatherAdapter(mList)
                Log.i(TAG + "2", mList.toString())
            }

        }


        mAdapter = WeatherAdapter(mList)
        //set data to adapter and show in recycler view
        binding.recyclerid.adapter = WeatherAdapter(mList)
        binding.recyclerid.layoutManager = LinearLayoutManager(context)

    }


    // get weather to hour but it back null
    fun callWeatherHour(call: ServiceApis, location: String)
    {
      call.getHour(location).enqueue(object :Callback<Hour>{
          @SuppressLint("SuspiciousIndentation")
          override fun onResponse(call: Call<Hour>, response: Response<Hour>) {
              if (response.isSuccessful) {
                  // get data from api response
                 var data = response.body()!!
                  dataOfHour(data)
                  Log.i(TAG + "RHou", response.body()!!.time.toString())
              } else {
                  Log.i(TAG + "N", "day Not response")
              }
          }

          override fun onFailure(call: Call<Hour>, t: Throwable) {
              TODO("Not yet implemented")
          }

      })
    }


    fun dataOfHour(data:Hour)
    {
        for (item in mListHour.size.until(4)) {
                mListHour.add(data)
                hourAdapter = WeatherHourAdapter(mListHour)
                Log.i(TAG + "3", mListHour.toString())
        }

        hourAdapter = WeatherHourAdapter(mListHour)
        //set data to adapter and show in recycler view
        binding.recycleridhoure.adapter = WeatherHourAdapter(mListHour)
        binding.recycleridhoure.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,true)
    }
    // get lat and long from yoyr location
    @SuppressLint("MissingPermission")
    private fun getLocation() {

        val locationManager = context?.let { LocationServices.getFusedLocationProviderClient(it!!) }
        locationManager!!.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                // Access location data:
                lat = location.latitude.toString()
                long = location.longitude.toString()
                val call: ServiceApis = Client.getRetrofit().create(ServiceApis::class.java)
                var local = "$lat,$long"
                presenter.callWeather(call,local)
                callWeatherDay(call, local)
                callWeatherHour(call,local)
                // Update UI or perform location-based actions
            } else {
                // Location not available, handle failure (optional)
                Toast.makeText(context, "Location unavailable", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { exception ->
            // Handle location retrieval failure (optional)
            Log.e("Location Error", "Failed to get location", exception)
        }
    }

    override fun viewWeatherOneDay(weatherModelNew: WeatherModelNew) {
        binding.location.text = weatherModelNew.location!!.country.toString()
        binding.temperature.text = weatherModelNew.current!!.tempC.toString()
        binding.cloud.text = "Cloud " + weatherModelNew.current.cloud.toString()
        binding.Wind.text = "Wind " + weatherModelNew.current.windKph.toString()
        binding.time.text = weatherModelNew.location.localtime.toString()

        Glide.with(this).load("https:" + weatherModelNew.current.condition!!.icon)
                .into(binding.imageView)


    }
}










