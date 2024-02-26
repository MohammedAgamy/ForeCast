package com.example.forecast.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.forecast.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment = HomeFragment()
        binding.animationView.playAnimation()
        binding.animationView


        binding.btnStart.setOnClickListener {
            Log.i("frg" , "Done")
           var intent = Intent(this , HomeActivity::class.java)
            startActivity(intent)

        }
    }
}