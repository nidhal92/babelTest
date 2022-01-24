package com.example.mertkesgin.weatherapp.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mertkesgin.weatherapp.domain.weather.usecase.InitLocationUseCase
import kotlinx.coroutines.launch

class SplashViewModel @ViewModelInject constructor(
    private val putLocationUseCase: InitLocationUseCase
) : ViewModel() {

    private val _uiState: MutableLiveData<SplashViewState> = MutableLiveData()
    val uiState: LiveData<SplashViewState>
        get() = _uiState

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather() = viewModelScope.launch {
        _uiState.value = SplashViewState(isLoading = true)
        val result = putLocationUseCase()
        _uiState.value = SplashViewState(isLoading = false)
    }
}
