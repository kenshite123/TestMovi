package com.example.testmovi.data.cache

import com.example.testmovi.domain.model.ForecastDaily
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CacheServiceImpl(
    private val fileService: FileService,
    private val gson: Gson
) : CacheService {
    companion object {
        private const val SUFFIX = ".c9"
    }

    override fun getForecast(lat: Double, lng: Double): ForecastDaily? {
        val file = "${lat}_${lng}$SUFFIX"
        val data = fileService.getData(file) ?: return null
        val type = object : TypeToken<ForecastDaily>() {}.type
        return gson.fromJson(data, type)
    }

    override fun saveForecast(lat: Double, lng: Double, forecastDaily: ForecastDaily) {
        val data = gson.toJson(forecastDaily)
        val file = "${lat}_${lng}$SUFFIX"
        fileService.saveData(file, data)
    }

    override fun getForecast(query: String): ForecastDaily? {
        val file = query + SUFFIX
        val data = fileService.getData(file) ?: return null
        val type = object : TypeToken<ForecastDaily>() {}.type
        return gson.fromJson(data, type)
    }

    override fun saveForecast(query: String, forecastDaily: ForecastDaily) {
        val data = gson.toJson(forecastDaily)
        val file = query + SUFFIX
        fileService.saveData(file, data)
    }
}