package com.example.mertkesgin.weatherapp.domain.weather.repository

import com.example.mertkesgin.weatherapp.data.weather.remote.response.CurrentWeatherResponse


interface LocationRepository {
    suspend fun initLocation()
    suspend fun removeLocation(location: String)
    suspend fun addLocation(location: String): ArrayList<CurrentWeatherResponse>
}
