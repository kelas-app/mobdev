package com.example.capstone.data.api.services

data class ProductRequest (
    val name:String,
    val description: String,
    val price : Float,
    val category: String,
    val productImage: String
)