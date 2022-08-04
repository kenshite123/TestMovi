package com.example.testmovi.data.di

import com.example.testmovi.data.db.DatabaseServiceImpl
import org.koin.dsl.module

val dbModule = module {
    single { DatabaseServiceImpl() }
}