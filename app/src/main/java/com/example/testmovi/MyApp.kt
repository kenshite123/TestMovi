package com.example.testmovi

import androidx.multidex.MultiDexApplication
import com.example.testmovi.data.di.cacheModule
import com.example.testmovi.data.di.dbModule
import com.example.testmovi.data.di.networkModule
import com.example.testmovi.data.di.repositoryModule
import com.example.testmovi.data.prefs.PrefUtil
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
                dbModule
            )
        }

        PrefUtil.init(this)
    }
}