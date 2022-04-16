package ru.nstu.ordsys

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.nstu.ordsys.di.appModule
import ru.nstu.ordsys.di.networkModule
import ru.nstu.ordsys.di.routersModule
import ru.nstu.ordsys.features.dishes.menu.di.dishesMenuModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)

            modules(
                appModule,
                networkModule,
                routersModule,
                dishesMenuModule
            )
        }
    }
}