package com.example.testmovi.data.repository

import com.example.testmovi.data.cache.CacheService
import com.example.testmovi.data.services.WeatherService
import com.example.testmovi.domain.model.ForecastDaily
import com.example.testmovi.domain.repository.WeatherRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private val gson: Gson,
    private val weatherService: WeatherService,
    private val cacheServices: CacheService
) : WeatherRepository, BaseRepository(gson) {

    @Throws(Throwable::class)
    override suspend fun getForecastDaily(lat: Double, lng: Double) : ForecastDaily? {
        val cache = cacheServices.getForecast(lat, lng)
        if (cache != null) {
            return cache
        }

//        val raw = weatherService.getForecastDaily(lat, lng).execute()
//        val data = networkTransform(raw)
        val data = withContext(Dispatchers.IO) {
            weatherService.getForecastDaily(lat, lng)
        }
        cacheServices.saveForecast(lat, lng, data)
        return data
    }

    @Throws(Throwable::class)
    override suspend fun searchForecastDaily(query: String) : ForecastDaily? {
        val cache = cacheServices.getForecast(query)
        if (cache != null) {
            return cache
        }

        val data = withContext(Dispatchers.IO) {
            weatherService.searchWeather(query = query)
        }
        cacheServices.saveForecast(query, data)
        return data
    }
}