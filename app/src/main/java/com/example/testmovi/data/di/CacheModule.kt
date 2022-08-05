package com.example.testmovi.data.di

import com.example.testmovi.data.cache.CacheService
import com.example.testmovi.data.cache.CacheServiceImpl
import com.example.testmovi.data.cache.FileService
import com.example.testmovi.data.cache.FileServiceImpl
import org.koin.dsl.module

val cacheModule = module {
//    single { PrefUtil() }
    single { CacheServiceImpl(get(), get()) as CacheService }
    single { FileServiceImpl(get()) as FileService }
}