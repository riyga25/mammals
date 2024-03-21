package com.app.rusmammals

import android.app.Application
import com.app.rusmammals.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import rusmammals.data.di.dataModule
import rusmammals.domain.di.domainModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        startKoin {
            androidContext(this@App)
            modules(
                presentationModule,
                dataModule,
                domainModule
            )
        }
    }
}