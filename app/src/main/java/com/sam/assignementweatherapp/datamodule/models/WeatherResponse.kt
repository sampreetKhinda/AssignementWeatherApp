package com.sam.assignementweatherapp.datamodule.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherResponse(
    @SerializedName("visibility")
    val visibility: Int = 0,
    @SerializedName("timezone")
    val timezone: Int = 0,
    @SerializedName("main")
    val main: Main,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("dt")
    val dt: Int = 0,
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("weather")
    val weather: List<WeatherItem>?,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("cod")
    val cod: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("base")
    val base: String = "",
    @SerializedName("wind")
    val wind: Wind
) : Serializable