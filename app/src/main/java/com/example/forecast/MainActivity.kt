package com.example.forecast

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.forecast.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment = HOMEFragment()
        binding.animationView.playAnimation()
        binding.animationView
        binding.btnStart.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, fragment)
                .commit()

        }
    }
}