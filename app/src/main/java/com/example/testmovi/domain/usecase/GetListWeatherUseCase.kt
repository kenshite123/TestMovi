package com.example.testmovi.domain.usecase

import com.example.testmovi.domain.model.Coord
import com.example.testmovi.domain.model.ForecastDaily
import com.example.testmovi.domain.repository.WeatherRepository

class GetListWeatherUseCase(private val repository: WeatherRepository) :
    BaseUseCase<Coord, ForecastDaily>() {
    override suspend fun loadData(params: Coord?): ForecastDaily? {
        return repository.getForecastDaily(params?.lat ?: 0.0, params?.lon ?: 0.0)
    }
}