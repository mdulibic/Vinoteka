package com.example.vinoteka.networking.model

import android.graphics.Bitmap
import com.example.vinoteka.model.Maltster
import com.example.vinoteka.model.Sort

data class WineResponse(
    val id: Long,
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
    val image: Bitmap,
    val sort: Sort
)