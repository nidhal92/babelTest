package com.example.mertkesgin.weatherapp.domain.weather.usecase

import com.example.mertkesgin.weatherapp.domain.weather.repository.LocationRepository
import javax.inject.Inject

class InitLocationUseCase @Inject constructor(
    private val weatherRepository: LocationRepository
) {
    suspend operator fun invoke() {
        weatherRepository.initLocation()
    }
}
