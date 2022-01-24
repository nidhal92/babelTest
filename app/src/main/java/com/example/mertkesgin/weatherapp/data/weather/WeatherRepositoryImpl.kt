package com.example.mertkesgin.weatherapp.data.weather

import com.example.mertkesgin.weatherapp.core.Constant
import com.example.mertkesgin.weatherapp.data.weather.remote.WeatherRemoteDataSource
import com.example.mertkesgin.weatherapp.data.weather.remote.response.CurrentWeatherResponse
import com.example.mertkesgin.weatherapp.data.weather.shared_preferences.UnitProvider
import com.example.mertkesgin.weatherapp.domain.weather.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
    val unitProvider: UnitProvider
) : WeatherRepository {

    override suspend fun fetchCurrentWeatherList(): ArrayList<CurrentWeatherResponse> {
        val responseList = arrayListOf<CurrentWeatherResponse>()
        unitProvider.get<ArrayList<String>>(Constant.LOCATION)?.forEach {
            responseList.add(remoteDataSource.fetchCurrentWeather(it))
        }
        return responseList
    }

    override suspend fun fetchCurrentWeather(location: String) =
        remoteDataSource.fetchCurrentWeather(location)
}
