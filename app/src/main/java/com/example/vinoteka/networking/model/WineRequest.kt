package com.example.vinoteka.networking.model

data class WineRequest(
    val name: String,
    val harvest: Int,
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