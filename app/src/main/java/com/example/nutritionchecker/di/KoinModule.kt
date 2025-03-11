package com.example.nutritionchecker.di

import com.example.nutritionchecker.BuildConfig
import com.example.nutritionchecker.NutritionViewModel
import com.example.nutritionchecker.network.RetrofitClient
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    // Retrofit client
    single { RetrofitClient.provideRetrofit(get()) }

    // OkHttpClient with API key interceptor
    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("X-Api-Key", BuildConfig.X_API_KEY) // Replace with your API key
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    // ApiService
    single { RetrofitClient.provideApiService(get()) }

    // ViewModel
    viewModel { NutritionViewModel(get()) }
}