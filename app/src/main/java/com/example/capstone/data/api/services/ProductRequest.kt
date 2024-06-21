package com.example.capstone.data.api.services
import java.io.File

data class ProductRequestRecommend (
    val userId : String
)
data class ProductRequest(
    val name:String,
    val description: String,
    val price: Float,
    val category: String,
    val productImage: List<String>,
    val isVisible: Boolean,
    var isCompleted: Boolean,
    val sellerId: String
)

data class AddProduct(
    val name: String,
    val description: String,
    val price: Float,
    val category: String,
    val productImage: File
)



/*
data class SellerItem(
    val title: String,
    val category: String,
    val price: String,
    var status: Int // 0 = dijual, 1 = diproses, 2 = selesai
)*/
