package com.example.capstone.view.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.api.response.CartItem
import com.example.capstone.data.repository.ProductRepository
import kotlinx.coroutines.launch

class CartViewModel(private val productRepository: ProductRepository) : ViewModel() {

    // Example method to add item to cart
    fun addItemToCart(productId: String): LiveData<String> {
        val resultLiveData = MutableLiveData<String>()
        viewModelScope.launch {
            try {
                val result = productRepository.addItemToCart(productId)
                resultLiveData.postValue(result)
            } catch (e: Exception) {
                // Handle error
                resultLiveData.postValue("Error: ${e.message}")
            }
        }
        return resultLiveData
    }
}


