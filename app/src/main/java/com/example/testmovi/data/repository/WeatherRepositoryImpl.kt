package com.example.testmovi.data.repository

import com.example.testmovi.data.services.WeatherService
import com.example.testmovi.domain.repository.WeatherRepository
import com.google.gson.Gson

class WeatherRepositoryImpl(
    private val gson: Gson,
    private val weatherService: WeatherService
) : WeatherRepository {
}