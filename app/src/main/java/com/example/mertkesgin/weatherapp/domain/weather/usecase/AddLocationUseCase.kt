package com.example.mertkesgin.weatherapp.domain.weather.usecase

import com.example.mertkesgin.weatherapp.domain.weather.mapper.CurrentWeatherMapper
import com.example.mertkesgin.weatherapp.domain.weather.model.CurrentWeather
import com.example.mertkesgin.weatherapp.domain.weather.repository.LocationRepository
import javax.inject.Inject

class AddLocationUseCase @Inject constructor(
    private val weatherRepository: LocationRepository,
    private val currentWeatherMapper: CurrentWeatherMapper
) {
    suspend operator fun invoke(location: String): ArrayList<CurrentWeather> {
        return currentWeatherMapper.currentResponseListToCurrentWeatherList(
            currentListWeatherResponse = weatherRepository.addLocation(location)
        )
    }
}
