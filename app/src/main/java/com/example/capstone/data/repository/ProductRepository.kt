package com.example.capstone.data.repository

import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.api.response.UploadNewProductResponse
import com.example.capstone.data.api.services.AddProduct
import com.example.capstone.data.api.services.ProductApiService
import com.example.capstone.data.api.services.ProductRequest
import okhttp3.MultipartBody
import java.io.File

class ProductRepository(private val apiService: ProductApiService) {

    suspend fun getAllProducts(): List<GetAllProductResponseItem> {
        return apiService.getAllProducts()
    }

    suspend fun uploadNewProduct(name: String, description: String, price: Float, category: String, productImage: File): UploadNewProductResponse {
        val addProduct = AddProduct(name, description, price, category, productImage)
        return apiService.uploadNewProduct(addProduct)
    }

    // Add more methods as needed based on your API service endpoints

}