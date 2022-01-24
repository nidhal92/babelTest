package com.example.mertkesgin.weatherapp.domain.weather.mapper

import com.example.mertkesgin.weatherapp.data.weather.remote.response.CurrentWeatherResponse
import com.example.mertkesgin.weatherapp.domain.weather.model.CurrentWeather
import javax.inject.Inject

class CurrentWeatherMapper @Inject constructor() {

    fun currentWeatherResponseToCurrentWeather(
        currentWeatherResponse: CurrentWeatherResponse
    ): CurrentWeather {

        return CurrentWeather(
            name = currentWeatherResponse.dataResponse?.request?.get(0)?.query ?: "",
            temperature = currentWeatherResponse?.dataResponse?.currentCondition?.get(0)?.tempC + '°'
                ?: "",
            weatherDesc = currentWeatherResponse?.dataResponse?.currentCondition?.get(0)?.weatherDesc?.get(
                0
            )?.value
                ?: "",
            weatherIcon = currentWeatherResponse?.dataResponse?.currentCondition?.get(0)?.weatherIconUrl?.get(
                0
            )?.value
                ?: "",
            windSpeed = currentWeatherResponse?.dataResponse?.currentCondition?.get(0)?.windSpeedKmPh + " KmPH"
                ?: "",
            humidity = currentWeatherResponse?.dataResponse?.currentCondition?.get(0)?.humidity + "%"
                ?: "",
            pressure = currentWeatherResponse?.dataResponse?.currentCondition?.get(0)?.pressure
                ?: ""
        )
    }

    fun currentResponseListToCurrentWeatherList(currentListWeatherResponse: ArrayList<CurrentWeatherResponse>): ArrayList<CurrentWeather> {
        val arrayList = arrayListOf<CurrentWeather>()
        currentListWeatherResponse.forEach {
            arrayList.add(currentWeatherResponseToCurrentWeather(it))
        }
        return arrayList
    }
}
