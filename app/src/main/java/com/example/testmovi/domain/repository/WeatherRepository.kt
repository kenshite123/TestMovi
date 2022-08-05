package com.example.testmovi.domain.repository

import com.example.testmovi.domain.model.ForecastDaily

interface WeatherRepository {
    suspend fun getForecastDaily(lat: Double, lng: Double) : ForecastDaily?
    suspend fun searchForecastDaily(query: String) : ForecastDaily?
}