package com.sam.assignementweatherapp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sam.assignementweatherapp.R
import com.sam.assignementweatherapp.databinding.ActivityMainBinding
import com.sam.assignementweatherapp.datamodule.models.WeatherResponse
import com.sam.assignementweatherapp.ui.viewmodel.MainViewModel
import com.sam.assignementweatherapp.utils.Constants
import com.sam.assignementweatherapp.utils.showAlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.btnGetWeather.setOnClickListener {
            if (binding.etCity.text.toString().isNotEmpty()) {
                binding.progressBar.visibility = View.VISIBLE
                viewModel.collectWeatherData(binding.etCity.text.toString())
                viewModel.saveCity(binding.etCity.text.toString())
            } else {
                showAlertDialog(
                    this,
                    getString(R.string.error_text),
                    getString(R.string.please_enter_city_name)
                )
            }

        }

        viewModel.cityDetailResponse?.observe(this, Observer {
            if (it != null) {
                viewModel.getWeatherReport(it)
            } else {
                binding.progressBar.visibility = View.GONE
                initEmptyViews()
                showAlertDialog(
                    this,
                    getString(R.string.error_text),
                    getString(R.string.please_enter_valid_city_name)
                )
            }
        })
        viewModel.weatherLiveData?.observe(this, Observer {
            if (it != null) {
                binding.progressBar.visibility = View.GONE
                initViews(it)
            }
        })

        setCity()
    }

    private fun initViews(weatherResponse: WeatherResponse) {
        binding.tvCity.text = weatherResponse.name
        binding.tvTemp.text =
            "${weatherResponse.main.temp}${Constants.EMPTY_SPACE}${getString(R.string.degree_celsius_symbol)}"
        Glide.with(this).load(
            Constants.WEATHER_ICON_BASE_URL
                    + weatherResponse.weather?.first()?.icon + Constants.PNG_IMAGE_EXTENSION
        ).into(binding.imgWeather)
        binding.tvWeather.text = weatherResponse.weather?.first()?.description
    }

    private fun initEmptyViews() {
        binding.tvCity.text = getString(R.string.no_data_symbol)
        binding.tvTemp.text = getString(R.string.no_data_symbol)
        binding.imgWeather.setImageResource(R.drawable.emptyimage)
        binding.tvWeather.text = getString(R.string.no_data_symbol)
    }

    private fun setCity() {
        if (viewModel.getCity() == Constants.EMPTY_STRING) {
            binding.etCity.setText(Constants.DEFAULT_CITY)
        } else {
            binding.etCity.setText(viewModel.getCity())
        }
        binding.btnGetWeather.performClick()
    }
}