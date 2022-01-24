package com.example.mertkesgin.weatherapp.ui.citiesList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mertkesgin.weatherapp.domain.weather.model.CurrentWeather
import com.example.mertkesgin.weatherapp.domain.weather.usecase.AddLocationUseCase
import com.example.mertkesgin.weatherapp.domain.weather.usecase.GetCurrentWeatherListUseCase
import com.example.mertkesgin.weatherapp.domain.weather.usecase.RemoveLocationUseCase
import kotlinx.coroutines.launch

class ListViewModel @ViewModelInject constructor(
    private val getWeatherListUseCase: GetCurrentWeatherListUseCase,
    private val addLocationUseCase: AddLocationUseCase,
    private val removeLocationUseCase: RemoveLocationUseCase
) : ViewModel() {
    private val _uiState: MutableLiveData<CurrentWeatherListViewState> = MutableLiveData()
    private val _uiFilteredState: MutableLiveData<CurrentWeatherListViewState> = MutableLiveData()
    private val _removeFroListUiState: MutableLiveData<RemoveLocationViewState> = MutableLiveData()
    private val _addListUiState: MutableLiveData<CurrentWeatherListViewState> = MutableLiveData()
    val uiState: LiveData<CurrentWeatherListViewState>
        get() = _uiState
    val uiFilteredState: LiveData<CurrentWeatherListViewState>
        get() = _uiFilteredState
    val removeFroListUiState: LiveData<RemoveLocationViewState>
        get() = _removeFroListUiState
    val addListUiState: LiveData<CurrentWeatherListViewState>
        get() = _addListUiState

    init {
        getCurrentWeather()
    }

    fun removeLocation(location: String) = viewModelScope.launch {
        _removeFroListUiState.value = RemoveLocationViewState(isLoading = true)
        val result = removeLocationUseCase(location)
        _removeFroListUiState.value = RemoveLocationViewState(isLoading = false)
    }

    fun addLocation(location: String) = viewModelScope.launch {
        _addListUiState.value = CurrentWeatherListViewState(isLoading = true)
        val result = addLocationUseCase(location)
        _addListUiState.value =
            CurrentWeatherListViewState(isLoading = false, currentWeatherList = result)
    }

    fun filterList(nameLocation: String) = viewModelScope.launch {
        _uiFilteredState.value = CurrentWeatherListViewState(isLoading = true)
        val filtredList = uiState.value?.currentWeatherList?.filter { currentWeather ->
            currentWeather.name
                .substring(0, currentWeather.name.indexOf(",")).toUpperCase()
                .contains(nameLocation.toUpperCase())
        }
        _uiFilteredState.value = CurrentWeatherListViewState(
            isLoading = false,
            currentWeatherList = filtredList as ArrayList<CurrentWeather>?
        )
    }

    fun getCurrentWeather() = viewModelScope.launch {
        _uiState.value = CurrentWeatherListViewState(isLoading = true)
        val result = getWeatherListUseCase()
        _uiState.value = CurrentWeatherListViewState(isLoading = false, currentWeatherList = result)
    }
}
