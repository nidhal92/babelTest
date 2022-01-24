package com.example.mertkesgin.weatherapp.domain.weather.usecase

import com.example.mertkesgin.weatherapp.domain.weather.repository.LocationRepository
import javax.inject.Inject

class RemoveLocationUseCase @Inject constructor(
    private val weatherRepository: LocationRepository
) {
    suspend operator fun invoke(location: String) {
        weatherRepository.removeLocation(location)
    }
}
