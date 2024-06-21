package com.example.capstone.view.cart.keranjang

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.api.response.CartItem
import com.example.capstone.data.api.response.GetDetailProductResponse
import com.example.capstone.data.api.services.OrderRequest
import com.example.capstone.data.api.services.OrderResponse
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.repository.OrderRepository
import com.example.capstone.data.repository.ProductRepository
import kotlinx.coroutines.launch

class KeranjangViewModel(
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository // Add OrderRepository
) : ViewModel() {

    private val _products = MutableLiveData<List<GetDetailProductResponse>>()
    val products: LiveData<List<GetDetailProductResponse>> = _products

    private val _orderResponse = MutableLiveData<OrderResponse>()
    val orderResponse: LiveData<OrderResponse> = _orderResponse

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

    fun createOrder(orderRequest: OrderRequest) {
        viewModelScope.launch {
            try {
                val response = orderRepository.createOrder(orderRequest)
                _orderResponse.postValue(response)
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}