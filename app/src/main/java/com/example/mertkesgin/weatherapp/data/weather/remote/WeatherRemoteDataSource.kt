package com.example.mertkesgin.weatherapp.data.weather.remote

import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    private val weatherApiService: WeatherApiService
) {
    suspend fun fetchCurrentWeather(location: String) =
        weatherApiService.getCurrentWeather(location)
}
