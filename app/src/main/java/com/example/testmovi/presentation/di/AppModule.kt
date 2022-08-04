package com.example.testmovi.presentation.di

import com.example.testmovi.presentation.main.MainViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Gson() }

    viewModel { MainViewModel(get()) }
}