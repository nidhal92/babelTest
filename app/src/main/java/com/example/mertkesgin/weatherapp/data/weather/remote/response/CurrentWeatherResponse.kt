package com.example.mertkesgin.weatherapp.data.weather.remote.response

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("data")
    var dataResponse: Data? = Data()

)

data class Data(
    @SerializedName("request")
    var request: ArrayList<Request>? = arrayListOf(),
    @SerializedName("current_condition")
    var currentCondition: ArrayList<CurrentCondition>? = arrayListOf()

)

data class Request(
    @SerializedName("type")
    var type: String? = "",
    @SerializedName("query")
    var query: String? = ""

)
data class WeatherValue(
    @SerializedName("value")
    var value: String? = ""

)

data class CurrentCondition(
    @SerializedName("observation_time")
    var observationTime: String? = "",
    @SerializedName("temp_C")
    var tempC: String? = "",
    @SerializedName("weatherIconUrl")
    var weatherIconUrl: ArrayList<WeatherValue>? = arrayListOf(),
    @SerializedName("weatherDesc")
    var weatherDesc: ArrayList<WeatherValue>? = arrayListOf(),
    @SerializedName("windspeedKmph")
    var windSpeedKmPh: String? = "",
    @SerializedName("humidity")
    var humidity: String? = "",
    @SerializedName("pressure")
    var pressure: String? = ""
)
