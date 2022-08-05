package com.example.testmovi.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmovi.domain.model.Coord
import com.example.testmovi.domain.response.DataResult
import com.example.testmovi.domain.usecase.GetListWeatherUseCase
import com.example.testmovi.domain.usecase.SearchWeatherUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val getListWeatherUseCase: GetListWeatherUseCase,
    private val searchWeatherUseCase: SearchWeatherUseCase
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, error ->
        _dataForecast.value = DataResult.Error(error)
    }

    private val _dataForecast = MutableLiveData<DataResult>()
    val dataForecast: LiveData<DataResult> = _dataForecast

    fun getForecastDaily(lat: Double, lng: Double) {
        viewModelScope.launch(Dispatchers.Main + exceptionHandler) {
            getListWeatherUseCase.execute(Coord(lat = lat, lon = lng), {
                _dataForecast.value = it
            }, {
                _dataForecast.value = it
            })
        }
    }

    fun searchWeather(query: String) {
        viewModelScope.launch(Dispatchers.Main + exceptionHandler) {
            searchWeatherUseCase.execute(query, {
                _dataForecast.value = it
            }, {
                _dataForecast.value = it
            })
        }
    }
}