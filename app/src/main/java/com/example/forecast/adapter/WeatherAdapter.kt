package com.example.forecast.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.forecast.R
import com.example.forecast.newmodel.listofweather.*
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext

class WeatherAdapter(var list: ArrayList<ListOfWeather>) : RecyclerView.Adapter<WeatherAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_wearher, parent, false)
        return MyHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.time.text = list[position].forecast!!.forecastday!![position].date
        holder.tump.text = list[position].forecast!!.forecastday!![position].day!!.avgtempC.toString()
        holder.mode.text = list[position].forecast!!.forecastday!![position].day!!.condition!!.text

       Picasso.get()
            .load("https:"+ list[position].forecast!!.forecastday!![position].day!!.condition!!.icon)
            .into(holder.image);



    }


    class MyHolder(item_View: View) : RecyclerView.ViewHolder(item_View) {
        var time = item_View.findViewById<TextView>(R.id.time_item)
        var tump: TextView = item_View.findViewById(R.id.temperature_item)
        var image: ImageView = item_View.findViewById(R.id.imageView_item)
        var mode: TextView = item_View.findViewById(R.id.mode_item)
    }

}