package com.example.capstone.data.api.response

import com.google.gson.annotations.SerializedName

data class CartItem(
    @SerializedName("productId") val productId: String,
    @SerializedName("_id") val id: String
)