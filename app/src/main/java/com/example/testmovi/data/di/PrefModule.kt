package com.example.testmovi.data.di

import com.example.testmovi.data.prefs.PrefUtilImpl
import com.example.testmovi.data.prefs.PrefUtilService
import org.koin.dsl.module

/**
 * @author BCU2HC
 * Created 9/21/2022 at 2:52 PM
 */

val prefModule = module {
    single { PrefUtilImpl(get()) as PrefUtilService }
}