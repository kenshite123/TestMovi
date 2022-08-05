package com.example.testmovi.domain.usecase

import com.example.testmovi.domain.model.ForecastDaily
import com.example.testmovi.domain.repository.WeatherRepository

class SearchWeatherUseCase(private val repository: WeatherRepository) :
    BaseUseCase<String, ForecastDaily>() {
    override suspend fun loadData(params: String?): ForecastDaily? {
        return repository.searchForecastDaily(params ?: "")
    }
}