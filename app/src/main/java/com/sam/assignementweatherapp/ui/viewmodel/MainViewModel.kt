package com.sam.assignementweatherapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sam.assignementweatherapp.datamodule.models.CityDetailResponse
import com.sam.assignementweatherapp.datamodule.models.WeatherResponse
import com.sam.assignementweatherapp.datamodule.repository.GeoRepository
import com.sam.assignementweatherapp.datamodule.repository.LocalDataRepository
import com.sam.assignementweatherapp.datamodule.repository.LocalDataRepository.get
import com.sam.assignementweatherapp.datamodule.repository.LocalDataRepository.set
import com.sam.assignementweatherapp.datamodule.repository.WeatherRepository
import com.sam.assignementweatherapp.utils.Constants
import com.sam.assignementweatherapp.utils.Constants.CITY_KEY
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val weatherRepository: WeatherRepository = WeatherRepository()
    private val geoRepository: GeoRepository = GeoRepository()
    var weatherLiveData: MutableLiveData<WeatherResponse>? = MutableLiveData()
    var cityDetailResponse: MutableLiveData<CityDetailResponse>? = MutableLiveData()

    fun collectWeatherData(cityName: String) {
        val scope = CoroutineScope(SupervisorJob() + CoroutineName("WeatherHelper"))
        scope.launch {
            val result = geoRepository.getCityDetails(cityName)
            if (result != null) {
                withContext(Dispatchers.Main) {
                    if (!result.body().isNullOrEmpty()) {
                        cityDetailResponse?.value = result.body()?.first()
                    } else {
                        cityDetailResponse?.value = null
                    }
                }
            }
        }
    }

    fun getWeatherReport(cityDetailResponse: CityDetailResponse) {
        val scope = CoroutineScope(SupervisorJob() + CoroutineName("WeatherHelper"))
        scope.launch {
            val result = weatherRepository.getWeatherUpdates(cityDetailResponse)
            if (result != null) {
                withContext(Dispatchers.Main) {
                    weatherLiveData?.value = result.body()
                }
            }
        }
    }

    fun saveCity(cityName: String) {
        LocalDataRepository.preferences[CITY_KEY] = cityName
    }

    fun getCity(): String = LocalDataRepository.preferences[CITY_KEY, Constants.EMPTY_STRING]
}