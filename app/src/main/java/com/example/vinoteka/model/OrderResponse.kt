package com.example.vinoteka.model

import java.util.Date

data class OrderResponse(
    val id: Long,
    val orderStatus: OrderStatus,
    val totalPrice: Double,
    val paymentMethod: PaymentMethod,
    val creationTimestamp: Date
)

enum class OrderStatus(val status: String) {
    CREATED(status = "Created"),
    PROCESSING(status = "Processing"),
    DELIVERED(status = "Delivered"),
    CANCELED(status = "Canceled"),
}

enum class PaymentMethod(val method: String) {
    CASH(method = "cash"),
    CARD(method = "card"),
}
