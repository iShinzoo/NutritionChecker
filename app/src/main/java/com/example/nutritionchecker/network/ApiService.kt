package com.example.nutritionchecker.network

import com.example.nutritionchecker.model.Nutrition
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


interface ApiService {

    @GET("v1/nutrition")
    suspend fun getNutritionInfo(@Query("query") query: String): Nutrition
    @Multipart
    @POST("v1/imagetextnutrition")
    suspend fun getImageNutritionInfo(
        @Part media: MultipartBody.Part
    ): Nutrition

}