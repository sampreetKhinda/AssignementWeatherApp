package com.sam.assignementweatherapp.datamodule.models

import com.google.gson.annotations.SerializedName

data class CityDetailResponse(
    @SerializedName("country")
    val country: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("lon")
    val lon: Double = 0.0,
    @SerializedName("state")
    val state: String = "",
    @SerializedName("lat")
    val lat: Double = 0.0
)