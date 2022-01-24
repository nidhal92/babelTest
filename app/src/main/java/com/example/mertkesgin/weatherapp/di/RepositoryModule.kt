package com.example.mertkesgin.weatherapp.di

import com.example.mertkesgin.weatherapp.data.weather.LocationRepositoryImpl
import com.example.mertkesgin.weatherapp.data.weather.WeatherRepositoryImpl
import com.example.mertkesgin.weatherapp.data.weather.remote.WeatherRemoteDataSource
import com.example.mertkesgin.weatherapp.data.weather.shared_preferences.UnitProvider
import com.example.mertkesgin.weatherapp.domain.weather.repository.LocationRepository
import com.example.mertkesgin.weatherapp.domain.weather.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun provideWeatherRepository(
        remoteDataSource: WeatherRemoteDataSource,
        unitProvider: UnitProvider
    ): WeatherRepository {
        return WeatherRepositoryImpl(remoteDataSource, unitProvider)
    }

    @Provides
    fun provideLocationRepository(
        remoteDataSource: WeatherRemoteDataSource,
        unitProvider: UnitProvider
    ): LocationRepository {
        return LocationRepositoryImpl(remoteDataSource, unitProvider)
    }
}
