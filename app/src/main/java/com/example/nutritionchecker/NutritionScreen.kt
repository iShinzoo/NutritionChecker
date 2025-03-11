package com.example.nutritionchecker

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutritionchecker.model.Item
import com.example.nutritionchecker.model.Nutrition

@Composable
fun NutritionScreen() {
    val viewModel: NutritionViewModel = viewModel()
    var query by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Nutrition?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri = it }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Enter food or drink items") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                keyboardController?.hide()
            })
        )

        Button(
            onClick = { imagePickerLauncher.launch("image/*") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Scan Image")
        }

        Button(
            onClick = {
                if (query.isNotBlank()) {
                    viewModel.fetchNutritionInfo(query,
                        onSuccess = { response ->
                            result = response
                            error = null
                        },
                        onError = { errorMessage ->
                            result = null
                            error = errorMessage
                        }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Search")
        }

        if (imageUri != null) {
            Button(
                onClick = {
                    viewModel.fetchImageNutritionInfo(imageUri!!,
                        onSuccess = { response ->
                            result = response
                            error = null
                        },
                        onError = { errorMessage ->
                            result = null
                            error = errorMessage
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Process Image")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        result?.let { NutritionInfoCard(it) }
        error?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
    }
}

@Composable
fun NutritionInfoCard(nutrition: Nutrition) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Results:", style = MaterialTheme.typography.bodyMedium)
            nutrition.items.forEach {
                NutritionItemRow(it)
            }
        }
    }
}

@Composable
fun NutritionItemRow(item: Item) {
    Column(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(text = "Name: ${item.name}")
        Text(text = "Serving Size: ${item.serving_size_g} grams")
        Text(text = "Calories: ${item.calories}")
        Text(text = "Carbohydrates: ${item.carbohydrates_total_g}")
        Text(text = "Cholesterol: ${item.cholesterol_mg}")
        Text(text = "Saturated Fat: ${item.fat_saturated_g}")
        Text(text = "Total Fat: ${item.fat_total_g}")
        Text(text = "Fiber: ${item.fiber_g}")
        Text(text = "Potassium: ${item.potassium_mg}")
        Text(text = "Protein: ${item.protein_g}")
        Text(text = "Sodium: ${item.sodium_mg}")
        Text(text = "Sugar: ${item.sugar_g}")
    }
}