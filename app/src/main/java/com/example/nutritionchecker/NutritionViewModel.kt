package com.example.nutritionchecker

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritionchecker.model.Nutrition
import com.example.nutritionchecker.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class NutritionViewModel(
    private val apiService: ApiService
) : ViewModel() {

    fun fetchNutritionInfo(query: String, onSuccess: (Nutrition) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getNutritionInfo(query)
                }
                onSuccess(response)
            } catch (e: Exception) {
                onError("Failed to fetch nutrition information. Please try again later.")
            }
        }
    }

    fun fetchImageNutritionInfo(imageUri: Uri, onSuccess: (Nutrition) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    val file = File(imageUri.path ?: "")
                    val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
                    val body = MultipartBody.Part.createFormData("media", file.name, requestFile)
                    apiService.getImageNutritionInfo(body)
                }
                onSuccess(response)
            } catch (e: Exception) {
                onError("Failed to process image. Please try again later.")
            }
        }
    }
}