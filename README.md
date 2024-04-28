# RunTrack

NutritionChecker is a Fitness Tracking app designed to provide instant access to comprehensive nutrition information for any food item. Whether you're tracking your macros, managing dietary requirements, or simply curious about what's on your plate, NutritionChecker has got you covered!

## Features
🍏 Instant access to nutrition information for any food item
🥑 Accurate data sourced from trusted APIs
📊 Detailed breakdown of macronutrients and micronutrients
🎨 Minimalist UI design for intuitive navigation
💡 Personalized recommendations for healthier choices

## Demo Video
![enter image description here](https://github.com/iShinzoo/NutritionChecker/blob/master/assets/video_20240403_105617_edit%20(1).gif)


## Build With

[Kotlin](https://kotlinlang.org/):
As the programming language.

[Jetpack Compose](https://developer.android.com/jetpack/compose) :
To build UI.

[Jetpack Navigation](https://developer.android.com/jetpack/compose/navigation) :
For navigation between screens and deep linking.

[Calorie Ninjas API](https://developers.google.com/maps/documentation/android-sdk) :
To track user's Nutrition activity such as carbs, protiens and other macro and micro nutrients.

## Installation

```
Simple clone this app and open in Android Studio.
```



### Calorie Ninjas Integration

Do these steps if you want to show CalorieNinjas.

Overview
The Calorie Ninjas API provides detailed nutrition information for food and drink items based on user input. Users can query the API with a string containing one or more food or drink items, optionally specifying quantities. The API returns nutrition information for each item in the query.

Endpoint
The base URL for the Calorie Ninjas API is:
```
https://api.calorieninjas.com/v1/nutrition
```

Authentication
To access the API, you need to include an 'X-Api-Key' header with your request. You will receive an API key upon signing up for an account.

Query Parameters
query (required): A string containing food or drink items. You can optionally specify quantities before each item. If no quantity is specified, the default quantity is 100 grams. Queries cannot exceed 1500 characters.
Sample Request

```
GET /v1/nutrition?query=10oz onion and a tomato
Host: api.calorieninjas.com
X-Api-Key: YOUR_API_KEY
```
Sample Response

```
{
  "items": [
    {
      "sugar_g": 13.3,
      "fiber_g": 4,
      "serving_size_g": 283.495,
      "sodium_mg": 8,
      "name": "onion",
      "potassium_mg": 99,
      "fat_saturated_g": 0.1,
      "fat_total_g": 0.5,
      "calories": 126.7,
      "cholesterol_mg": 0,
      "protein_g": 3.9,
      "carbohydrates_total_g": 28.6
    },
    {
      "sugar_g": 2.6,
      "fiber_g": 1.2,
      "serving_size_g": 100,
      "sodium_mg": 4,
      "name": "tomato",
      "potassium_mg": 23,
      "fat_saturated_g": 0,
      "fat_total_g": 0.2,
      "calories": 18.2,
      "cholesterol_mg": 0,
      "protein_g": 0.9,
      "carbohydrates_total_g": 3.9
    }
  ]
}
```

You can make API calls using Kotlin.

Replace 'YOUR_API_KEY' with your actual API key obtained from Calorie Ninjas.


## Project Status

These features are left to be implemented:

1. imagetextnutrition.
2. Recipe.
