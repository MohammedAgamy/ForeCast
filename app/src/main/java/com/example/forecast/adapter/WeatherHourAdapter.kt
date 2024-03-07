package com.example.forecast.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forecast.R
import com.example.forecast.newmodel.listofweather.Forecastday
import com.example.forecast.newmodel.listofweather.Hour
import com.example.forecast.newmodel.listofweather.ListOfWeather
import com.squareup.picasso.Picasso

class WeatherHourAdapter (var list: ArrayList<Hour>) : RecyclerView.Adapter<WeatherHourAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_houre, parent, false)
        return MyHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.time.text = list[position].time
        holder.tump.text = list[position].tempC.toString()
        holder.mode.text = list[position].condition!!.text

        Picasso.get()
           .load("https:"+ list[position].condition!!.icon)
           .into(holder.image);



    }


    class MyHolder(item_View: View) : RecyclerView.ViewHolder(item_View) {
        var time = item_View.findViewById<TextView>(R.id.time_item_h)
        var tump: TextView = item_View.findViewById(R.id.temperature_item_h)
        var image: ImageView = item_View.findViewById(R.id.imageView_item_h)
        var mode: TextView = item_View.findViewById(R.id.mode_item_h)
    }

}