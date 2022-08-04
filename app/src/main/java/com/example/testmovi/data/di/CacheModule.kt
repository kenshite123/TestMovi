package com.example.testmovi.data.di

import com.example.testmovi.data.cache.CacheServiceImpl
import com.example.testmovi.data.cache.FileServiceImpl
import org.koin.dsl.module

val cacheModule = module {
//    single { PrefUtil() }
    single { CacheServiceImpl(get(), get()) }
    single { FileServiceImpl(get()) }
}