package com.example.nutritionchecker.network

import com.example.nutritionchecker.model.Nutrition
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("X-Api-Key: liPkqG/KPK42V66pIi9fUQ==yzCIzx8TFOi3YQRx")
    @GET("v1/nutrition")
    suspend fun getNutritionInfo(@Query("query") query: String): Nutrition

}
