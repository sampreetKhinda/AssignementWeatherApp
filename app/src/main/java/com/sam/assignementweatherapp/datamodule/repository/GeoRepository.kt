package com.sam.assignementweatherapp.datamodule.repository

import com.sam.assignementweatherapp.datamodule.NetworkCalls
import com.sam.assignementweatherapp.datamodule.models.CityDetailResponse
import com.sam.assignementweatherapp.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GeoRepository {

    private var serviceGeo: NetworkCalls? = null

    init {
        val retrofitGeo = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL_GEO)
            .build()
        serviceGeo = retrofitGeo.create(NetworkCalls::class.java)
    }

    suspend fun getCityDetails(cityName: String): Response<List<CityDetailResponse>>? =
        serviceGeo?.getCityDetail(cityName, Constants.API_KEY)

}