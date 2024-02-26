package com.example.forecast.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.forecast.R
import com.example.forecast.dataBase.Client
import com.example.forecast.dataBase.ServiceApis
import com.example.forecast.databinding.FragmentHOMEBinding
import com.example.forecast.newmodel.WeatherModelNew

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log


class HomeFragment : Fragment() {

    lateinit var  view1:TextView

    lateinit var binding:FragmentHOMEBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentHOMEBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view1=view.findViewById(R.id.location)

        super.onViewCreated(view, savedInstanceState)
        var call :ServiceApis =Client.getRetrofit().create(ServiceApis::class.java)


        call.getService().enqueue(object :Callback<WeatherModelNew>{
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<WeatherModelNew>, response: Response<WeatherModelNew>) {
                if (response.isSuccessful)
                {
                    binding.location.text =response.body()!!.location!!.country
                    binding.temperature.text=response.body()!!.current!!.tempC.toString()
                    binding.cloud.text="Cloud " + response.body()!!.current!!.cloud.toString()
                    binding.Wind.text="Wind " + response.body()!!.current!!.windKph.toString()
                    binding.time.text=response.body()!!.location!!.localtime
                    binding.mode.text =response.body()!!.current!!.condition!!.text

                }
                else
                {
                    Log.i("grtf" , "oge")

                }

            }

            override fun onFailure(call: Call<WeatherModelNew>, t: Throwable) {
                Log.i("grtRf" , t.message.toString())

            }

        })

    }
}


