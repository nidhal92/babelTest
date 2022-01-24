package com.example.mertkesgin.weatherapp.domain.weather.repository

import com.example.mertkesgin.weatherapp.data.weather.remote.response.CurrentWeatherResponse


interface WeatherRepository {
    suspend fun fetchCurrentWeatherList(): ArrayList<CurrentWeatherResponse>
    suspend fun fetchCurrentWeather(location: String): CurrentWeatherResponse
}
