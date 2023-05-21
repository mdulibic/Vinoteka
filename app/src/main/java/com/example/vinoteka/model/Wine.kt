package com.example.vinoteka.model

import android.graphics.Bitmap

data class Wine(
    val id: Long,
    val name: String,
    val harvest: Int,
    val alcoholPercentage: Float,
    val maltster: Maltster,
    val quality: String,
    val vineyard: String,
    val temperatureOfServing: String,
    val gastroRecommendation: String,
    val description: String,
    val price: Double,
    val sort: Sort
)
