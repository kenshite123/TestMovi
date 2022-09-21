package com.example.testmovi

import androidx.multidex.MultiDexApplication
import com.example.testmovi.data.di.*
import com.example.testmovi.data.prefs.PrefUtilImpl
import com.example.testmovi.domain.di.useCaseModule
import com.example.testmovi.presentation.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                appModule,
                useCaseModule,
                networkModule,
                repositoryModule,
                cacheModule,
                dbModule,
                prefModule
            )
        }

    }
}