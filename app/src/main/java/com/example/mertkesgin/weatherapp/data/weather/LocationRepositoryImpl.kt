package com.example.mertkesgin.weatherapp.data.weather

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mertkesgin.weatherapp.domain.weather.repository.LocationRepository
import com.example.mertkesgin.weatherapp.core.Constant
import com.example.mertkesgin.weatherapp.data.weather.remote.WeatherRemoteDataSource
import com.example.mertkesgin.weatherapp.data.weather.remote.response.CurrentWeatherResponse
import com.example.mertkesgin.weatherapp.data.weather.shared_preferences.UnitProvider
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
    val unitProvider: UnitProvider

) : LocationRepository {

    override suspend fun initLocation() {
        if (!unitProvider.existKey(Constant.LOCATION)) {
            val locationList = arrayListOf<String>()
            locationList.add(Constant.CASA)
            locationList.add(Constant.MARRAKECH)
            locationList.add(Constant.RABAT)
            locationList.add(Constant.TANGIER)
            locationList.add(Constant.FES)
            unitProvider.put(locationList, Constant.LOCATION)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun removeLocation(location: String) {
        val arrayLocation = unitProvider.get<ArrayList<String>>(Constant.LOCATION)
        arrayLocation?.removeIf {
            it.toUpperCase().contains(location.substring(0, location.indexOf(",")).toUpperCase())
        }
        unitProvider.put(arrayLocation, Constant.LOCATION)
    }

    override suspend fun addLocation(location: String): ArrayList<CurrentWeatherResponse> {
        val arrayLocation = unitProvider.get<ArrayList<String>>(Constant.LOCATION)
        arrayLocation?.add(location)
        unitProvider.put(arrayLocation, Constant.LOCATION)
        val responseList = arrayListOf<CurrentWeatherResponse>()
        unitProvider.get<ArrayList<String>>(Constant.LOCATION)?.forEach {
            responseList.add(remoteDataSource.fetchCurrentWeather(it))
        }
        return responseList
    }
}
