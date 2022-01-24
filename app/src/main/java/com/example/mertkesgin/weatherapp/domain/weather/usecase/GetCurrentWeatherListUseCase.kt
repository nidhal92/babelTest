package com.example.mertkesgin.weatherapp.domain.weather.usecase

import com.example.mertkesgin.weatherapp.domain.weather.mapper.CurrentWeatherMapper
import com.example.mertkesgin.weatherapp.domain.weather.model.CurrentWeather
import com.example.mertkesgin.weatherapp.domain.weather.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherListUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val currentWeatherMapper: CurrentWeatherMapper
) {
    suspend operator fun invoke(): ArrayList<CurrentWeather> {
        return currentWeatherMapper.currentResponseListToCurrentWeatherList(
            currentListWeatherResponse = weatherRepository.fetchCurrentWeatherList()
        )
    }
}
