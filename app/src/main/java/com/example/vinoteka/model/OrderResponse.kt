package com.example.vinoteka.model

import java.sql.Timestamp

data class OrderResponse(
    val id: String,
    val orderStatus: OrderStatus,
    val totalPrice: Long,
    val paymentMethod: PaymentMethod,
    val creationTimestamp: Timestamp,
)

enum class OrderStatus(val status: String) {
    CREATED(status = "created"),
    PROCESSED(status = "processed"),
    DELIVERED(status = "delivered"),
    CANCELED(status = "canceled"),
}

enum class PaymentMethod(val method: String) {
    CASH(method = "cash"),
    CARD(method = "card"),
}
