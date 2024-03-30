package com.example.nutritionchecker


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritionchecker.model.Nutrition
import com.example.nutritionchecker.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class NutritionViewModel : ViewModel() {

    fun fetchNutritionInfo(query: String, onSuccess: (Nutrition) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.service.getNutritionInfo(query)
                }
                onSuccess(response)
            } catch (e: Exception) {
                onError("Failed to fetch nutrition information. Please try again later.")
            }
        }
    }
}
