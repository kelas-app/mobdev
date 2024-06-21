package com.example.capstone.data.api.response

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("_id")
    val id: String,
    @SerializedName("buyerId")
    val buyerId: String,
    @SerializedName("sellerId")
    val sellerId: String,
    @SerializedName("productId")
    val productId: String,
    @SerializedName("totalPrice")
    val totalPrice: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("order_date")
    val orderDate: String
)