package com.example.testmovi.domain.di

import com.example.testmovi.domain.usecase.GetListWeatherUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetListWeatherUseCase(get()) }
}