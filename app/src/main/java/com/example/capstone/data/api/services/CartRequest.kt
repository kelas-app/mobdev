package com.example.capstone.data.api.services

import com.google.gson.annotations.SerializedName

data class CartRequest(
    @SerializedName("productId")
    val productId: String
)