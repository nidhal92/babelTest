package com.example.mertkesgin.weatherapp.data.weather.remote

import com.example.mertkesgin.weatherapp.core.Constant
import com.example.mertkesgin.weatherapp.core.Constant.API_ENDPOINT_CURRENT
import com.example.mertkesgin.weatherapp.data.weather.remote.response.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET(API_ENDPOINT_CURRENT)
    suspend fun getCurrentWeather(
        @Query("q") location: String,
        @Query("key") apiKey: String = Constant.API_KEY,
        @Query("format") header: String = Constant.FORMAT
    ): CurrentWeatherResponse
}
