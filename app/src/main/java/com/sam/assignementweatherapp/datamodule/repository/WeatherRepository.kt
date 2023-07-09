package com.sam.assignementweatherapp.datamodule.repository

import com.sam.assignementweatherapp.datamodule.NetworkCalls
import com.sam.assignementweatherapp.datamodule.models.CityDetailResponse
import com.sam.assignementweatherapp.datamodule.models.WeatherResponse
import com.sam.assignementweatherapp.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {
    private var serviceWeather: NetworkCalls? = null

    init {
        val retrofitWeather = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL_WEATHER)
            .build()
        serviceWeather = retrofitWeather.create(NetworkCalls::class.java)
    }

    suspend fun getWeatherUpdates(cityDetailResponse: CityDetailResponse): Response<WeatherResponse>? =
        serviceWeather?.getWeather(
            cityDetailResponse.lat,
            cityDetailResponse.lon,
            Constants.API_KEY,
            Constants.METRIC_UNITS
        )
}