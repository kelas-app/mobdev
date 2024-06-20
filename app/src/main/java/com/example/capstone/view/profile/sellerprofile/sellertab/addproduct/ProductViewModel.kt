package com.example.capstone.view.profile.sellerprofile.sellertab.addproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.api.response.UploadNewProductResponse
import com.example.capstone.data.api.services.AddProduct
import com.example.capstone.data.api.services.ProductApiService
import com.example.capstone.data.api.services.ProductRequest
import com.example.capstone.data.repository.ProductRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.File

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    /*fun uploadNewProduct(name: String, description: String, price: Float, category: String, productImage: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                val response = productRepository.uploadNewProduct(name, description, price, category, productImage)
                // Handle response as needed
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error case
            }
        }
    }*/
    fun uploadProduct(name: String, description: String, price: Float, category: String, productImage: File) {
        viewModelScope.launch {
            try {
                val response = productRepository.uploadNewProduct(name, description, price, category, productImage)
                // Handle the response
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }

}