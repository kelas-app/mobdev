package com.example.capstone.data.api.services

import com.google.gson.annotations.SerializedName

data class OrderRequest(
    @SerializedName("buyerId") val buyerId: String,
    @SerializedName("sellerId") val sellerId: String,
    @SerializedName("productId") val productId: String,
    @SerializedName("totalPrice") val totalPrice: String
)

data class OrderResponse(
    @SerializedName("buyerId") val buyerId: String,
    @SerializedName("sellerId") val sellerId: String,
    @SerializedName("productId") val productId: String,
    @SerializedName("totalPrice") val totalPrice: Int,
    @SerializedName("status") val status: String,
    @SerializedName("_id") val id: String,
    @SerializedName("order_date") val orderDate: String,
    @SerializedName("__v") val version: Int
)