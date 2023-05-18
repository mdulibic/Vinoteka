package com.example.vinoteka.networking.model

import com.example.vinoteka.model.Sort

data class AddWineRequest(
    val name: String,
    val harvest: String,
    val alcoholPercentage: Float,
    val maltster: String,
    val quality: String,
    val vineyard: String,
    val temperatureOfServing: String,
    val gastroRecommendation: String,
    val description: String,
    val price: Double,
    val sort: Long
)