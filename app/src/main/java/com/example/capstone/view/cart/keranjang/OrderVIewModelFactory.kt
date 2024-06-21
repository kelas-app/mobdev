package com.example.capstone.view.cart.keranjang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.data.repository.OrderRepository
import com.example.capstone.data.repository.ProductRepository
import com.example.capstone.view.cart.riwayat.OrderViewModel

class OrderViewModelFactory(private val productRepository: ProductRepository, private val orderRepository: OrderRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            return OrderViewModel(productRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}