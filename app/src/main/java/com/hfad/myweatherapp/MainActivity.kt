package com.hfad.myweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.myweatherapp.databinding.ActivityMainBinding
import com.hfad.myweatherapp.view.weatherlist.WeatherFragmentList

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        // binding.text.text = "сменил текст при помощи binding"

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherFragmentList.newInstance()).commit()
        }
    }
}