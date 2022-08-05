package com.example.testmovi.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForecastDaily(
    @SerializedName("city")
    @Expose
    var city: City?,
    @SerializedName("cod")
    @Expose
    var cod: String?,
    @SerializedName("message")
    @Expose
    var message: Any?,
    @SerializedName("cnt")
    @Expose
    var cnt: Int?,
    @SerializedName("list")
    @Expose
    var list: List<WeatherList>?,
)