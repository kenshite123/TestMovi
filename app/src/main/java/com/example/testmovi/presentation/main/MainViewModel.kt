package com.example.testmovi.presentation.main

import androidx.lifecycle.ViewModel
import com.example.testmovi.domain.usecase.GetListWeatherUseCase

class MainViewModel(private val useCase: GetListWeatherUseCase) : ViewModel() {
}