package com.example.capstone.data.api.response

import com.example.capstone.data.api.services.ProductRequest
import com.example.capstone.data.api.services.ProductRequestWithId

data class DashboardResponse(
    val dijual: List<ProductRequestWithId>,
    val diproses: List<ProcessRequest>,
    val selesai: List<ProcessRequest>
)

data class ProcessRequest(
    val _id: String,
    val buyerId: String,
    val sellerId: String,
    val productId: String,
    val totalPrice: Float,
    val status: String,
    val order_date: String,
    val __v: Int
)
