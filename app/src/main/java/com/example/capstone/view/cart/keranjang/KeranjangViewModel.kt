package com.example.capstone.view.cart.keranjang

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.api.response.CartItem
import com.example.capstone.data.api.response.GetDetailProductResponse
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.repository.ProductRepository
import kotlinx.coroutines.launch

class KeranjangViewModel(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _products = MutableLiveData<List<GetDetailProductResponse>>()
    val products: LiveData<List<GetDetailProductResponse>> = _products

    fun fetchCartItems() {
        viewModelScope.launch {
            try {
                val cartItems = productRepository.getCartItems()
                fetchProductDetails(cartItems)
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }

    private fun fetchProductDetails(cartItems: List<CartItem>) {
        viewModelScope.launch {
            val productList = mutableListOf<GetDetailProductResponse>()
            for (item in cartItems) {
                productRepository.getProductDetails(item.productId).collect { result ->
                    result.onSuccess { productDetails ->
                        productList.add(productDetails)
                    }.onFailure {
                        // Handle failure
                    }
                }
            }
            _products.postValue(productList)
        }
    }
}