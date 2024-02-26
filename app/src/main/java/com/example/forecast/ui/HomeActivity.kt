package com.example.forecast.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forecast.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var fragment= HomeFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.container,fragment)
            .commit()

    }
}