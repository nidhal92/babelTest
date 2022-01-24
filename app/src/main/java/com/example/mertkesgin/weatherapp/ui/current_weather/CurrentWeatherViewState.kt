package com.example.mertkesgin.weatherapp.ui.current_weather

import com.example.mertkesgin.weatherapp.domain.weather.model.CurrentWeather

data class CurrentWeatherViewState(
    val currentWeather: CurrentWeather? = null,
    val isLoading: Boolean,
    val error: String? = null
)
