package com.example.mertkesgin.weatherapp.domain.weather.model

import java.io.Serializable

data class CurrentWeather(
    val name: String,
    val temperature: String,
    val windSpeed: String,
    val pressure: String,
    val weatherDesc: String,
    val weatherIcon: String,
    val humidity: String
) : Serializable
