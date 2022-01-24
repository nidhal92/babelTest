package com.example.mertkesgin.weatherapp.ui.current_weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mertkesgin.weatherapp.domain.weather.usecase.GetCurrentWeatherUseCase
import kotlinx.coroutines.launch

class CurrentWeatherViewModel @ViewModelInject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _uiState: MutableLiveData<CurrentWeatherViewState> = MutableLiveData()
    val uiState: LiveData<CurrentWeatherViewState>
        get() = _uiState

    fun getCurrentWeather(location: String) = viewModelScope.launch {
        _uiState.value = CurrentWeatherViewState(isLoading = true)
        val result = getCurrentWeatherUseCase(location)
        _uiState.value = CurrentWeatherViewState(isLoading = false, currentWeather = result)
    }
}
