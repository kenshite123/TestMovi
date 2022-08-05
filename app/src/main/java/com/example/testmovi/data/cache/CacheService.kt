package com.example.testmovi.data.cache

import com.example.testmovi.domain.model.ForecastDaily


interface CacheService {
    fun getForecast(lat: Double, lng: Double): ForecastDaily?
    fun saveForecast(lat: Double, lng: Double, forecastDaily: ForecastDaily)

    fun getForecast(query: String): ForecastDaily?
    fun saveForecast(query: String, forecastDaily: ForecastDaily)
}