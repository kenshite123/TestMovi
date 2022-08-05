package com.example.testmovi.data.services

import com.example.testmovi.BuildConfig
import com.example.testmovi.domain.model.ForecastDaily
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/forecast/daily")
    suspend fun getForecastDaily(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") cnt: Int = 7,
        @Query("appid") appid: String = BuildConfig.APP_ID,
    ): ForecastDaily

    @GET("data/2.5/forecast/daily")
    suspend fun searchWeather(
        @Query("q") query: String? = null,
        @Query("cnt") cnt: Int = 7,
        @Query("appid") appid: String = BuildConfig.APP_ID,
    ): ForecastDaily
}