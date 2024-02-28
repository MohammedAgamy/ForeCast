package com.example.forecast.ui

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.forecast.adapter.WeatherAdapter
import com.example.forecast.dataBase.Client
import com.example.forecast.dataBase.ServiceApis
import com.example.forecast.databinding.FragmentHOMEBinding
import com.example.forecast.newmodel.WeatherModelNew

import com.example.forecast.newmodel.listofweather.ListOfWeather
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHOMEBinding
    var TAG: String = "TAG"
    lateinit var mAdapter: WeatherAdapter
    var mList: ArrayList<ListOfWeather> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHOMEBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val call: ServiceApis = Client.getRetrofit().create(ServiceApis::class.java)

        callWeather(call)
        callWeatherDay(call)
        getLocation()


    }

    fun callWeather(call: ServiceApis) {
        call.getService().enqueue(object : Callback<WeatherModelNew> {
            override fun onResponse(call: Call<WeatherModelNew>, response: Response<WeatherModelNew>) {
                if (response.isSuccessful) {

                    //binding.imageView.setImageURI(response.body()!!.current!!.condition!!.icon!!.toUri())
                   // Glide.with(context!!).load(response.body()!!.current!!.condition!!.icon).into(binding.imageView)

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
                if (response.isSuccessful) {
                    // Log.i(TAG + "R", response.body()!!.day!!.toString())
                    val data = response.body()!!
                    dataOfDays(data)

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

    fun dataOfDays(data: ListOfWeather) {
        for (item in data.toString()) {
            for (item in mList.size.until(10)) {
                for (i in item..mList.size.toString().length) {
                    mList.add(data)
                    Log.i(TAG + "R2", mList.toString())

                }


            }

        }


        mAdapter = WeatherAdapter(mList)
        binding.recyclerid.layoutManager = LinearLayoutManager(context)
        binding.recyclerid.adapter = WeatherAdapter(mList)
        binding.recyclerid.hasFixedSize()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val locationManager = context?.let { LocationServices.getFusedLocationProviderClient(it!!) }
        locationManager!!.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                // Access location data:
                val latitude = location.latitude
                val longitude = location.longitude
                Toast.makeText(context, latitude.toString(), Toast.LENGTH_SHORT).show()
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
}


