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
    val sellerId: String,
    var isForSale: Boolean,
    var _id: String
)

data class ProductRequestWithId(
    val name: String,
    val description: String,
    val price: Float,
    val category: String,
    val productImage: List<String>,  // Assuming productImage is a list of String (URLs)
    val isVisible: Boolean,
    var isCompleted: Boolean,
    val sellerId: String,  // Assuming sellerId is a String
    val isForSale: Boolean,
    val productId: String
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
