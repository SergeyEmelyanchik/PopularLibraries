package ru.geekbrains.popularlibraries.core

import android.app.Application
import ru.geekbrains.popularlibraries.di.AppComponent
import ru.geekbrains.popularlibraries.di.AppModule
import ru.geekbrains.popularlibraries.di.DaggerAppComponent


class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()

    }
}