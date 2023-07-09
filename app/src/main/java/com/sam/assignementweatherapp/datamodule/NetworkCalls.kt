package com.sam.assignementweatherapp.datamodule

import com.sam.assignementweatherapp.datamodule.models.CityDetailResponse
import com.sam.assignementweatherapp.datamodule.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkCalls {

    @GET("2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String?,
        @Query("units") units: String?
    ): Response<WeatherResponse>

    @GET("1.0/direct")
    suspend fun getCityDetail(
        @Query("q") city: String,
        @Query("appid") appId: String
    ): Response<List<CityDetailResponse>>
}