package com.example.capstone.data.repository

import com.example.capstone.data.api.response.DashboardResponse
import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.api.response.UploadNewProductResponse
import com.example.capstone.data.api.services.AddProduct
import com.example.capstone.data.api.services.ProductApiService
import com.example.capstone.data.api.services.ProductRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ProductRepository(private val apiService: ProductApiService) {

    suspend fun getAllProducts(): List<GetAllProductResponseItem> {
        return apiService.getAllProducts()
    }

    suspend fun uploadNewProduct(name: String, description: String, price: Float, category: String, productImage: File): UploadNewProductResponse {
        val namePart = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
        val descriptionPart = RequestBody.create("text/plain".toMediaTypeOrNull(), description)
        val pricePart = RequestBody.create("text/plain".toMediaTypeOrNull(), price.toString())
        val categoryPart = RequestBody.create("text/plain".toMediaTypeOrNull(), category)
        val imagePart = MultipartBody.Part.createFormData("productImage", productImage.name, RequestBody.create("image/*".toMediaTypeOrNull(), productImage))

        return apiService.uploadNewProduct(namePart, descriptionPart, pricePart, categoryPart, imagePart)
    }
    suspend fun getDashboardData(): DashboardResponse {
        return apiService.getDashboardData()
    }

}