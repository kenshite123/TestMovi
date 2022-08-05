package com.example.testmovi.domain.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class WeatherList(
    @SerializedName("clouds")
    @Expose
    var clouds: Int?,
    @SerializedName("deg")
    @Expose
    var deg: Int?,
    @SerializedName("dt")
    @Expose
    var dt: Long?,
    @SerializedName("feels_like")
    @Expose
    var feelsLike: FeelsLike?,
    @SerializedName("gust")
    @Expose
    var gust: Double?,
    @SerializedName("humidity")
    @Expose
    var humidity: Int?,
    @SerializedName("pop")
    @Expose
    var pop: Double?,
    @SerializedName("pressure")
    @Expose
    var pressure: Int?,
    @SerializedName("rain")
    @Expose
    var rain: Double?,
    @SerializedName("speed")
    @Expose
    var speed: Double?,
    @SerializedName("sunrise")
    @Expose
    var sunrise: Int?,
    @SerializedName("sunset")
    @Expose
    var sunset: Int?,
    @SerializedName("temp")
    @Expose
    var temp: Temp?,
    @SerializedName("weather")
    @Expose
    var weather: List<Weather?>?
)