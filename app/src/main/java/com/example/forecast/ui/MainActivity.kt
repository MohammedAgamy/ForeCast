package com.example.forecast.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.forecast.databinding.ActivityMainBinding
import com.google.android.gms.location.LocationServices


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    var lat :String? = null
    var long :String? = null
    var localLocation =lat+","+long
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //call LocationPermission
        requestLocationPermission()


        binding.animationView.animation
        binding.animationView.playAnimation()


        //go to weather activity
        binding.btnStart.setOnClickListener {
            Log.i("frg", "Done")
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }


    }


    fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        } else {
            // Permission already granted, proceed to access location
            getLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation()
            }
            // Permission granted, proceed to access location
        } else {
            // Permission denied, handle user rejection (optional)
            Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {

        val locationManager = this.let { LocationServices.getFusedLocationProviderClient(it!!) }
        locationManager!!.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                // Access location data:
                lat = location.latitude.toString()
                long = location.longitude.toString()
                Toast.makeText(this, lat + "," + long, Toast.LENGTH_SHORT).show()
                // Update UI or perform location-based actions
            } else {
                // Location not available, handle failure (optional)
                Toast.makeText(this, "Location unavailable", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { exception ->
            // Handle location retrieval failure (optional)
            Log.e("Location Error", "Failed to get location", exception)
        }
    }
}