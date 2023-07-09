package com.sam.assignementweatherapp

import android.app.Application
import com.sam.assignementweatherapp.datamodule.repository.LocalDataRepository

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LocalDataRepository.init(this)
    }
}