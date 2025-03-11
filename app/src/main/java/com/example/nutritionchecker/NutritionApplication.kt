package com.example.nutritionchecker

import android.app.Application
import com.example.nutritionchecker.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class NutritionApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NutritionApplication)
            modules(networkModule)
        }
    }
}