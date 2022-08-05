package com.example.testmovi.data.di

import com.example.testmovi.data.repository.WeatherRepositoryImpl
import com.example.testmovi.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { WeatherRepositoryImpl(get(), get(), get()) as WeatherRepository }
}