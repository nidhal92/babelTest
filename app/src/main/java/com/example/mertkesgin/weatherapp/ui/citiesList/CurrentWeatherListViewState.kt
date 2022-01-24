package com.example.mertkesgin.weatherapp.ui.citiesList

import com.example.mertkesgin.weatherapp.domain.weather.model.CurrentWeather

data class CurrentWeatherListViewState(
    val currentWeatherList: ArrayList<CurrentWeather>? = null,
    val isLoading: Boolean,
    val error: String? = null
)

data class RemoveLocationViewState(
    val isLoading: Boolean,
    val error: String? = null
)

data class AddLocationViewState(
    val isLoading: Boolean,
    val error: String? = null
)
