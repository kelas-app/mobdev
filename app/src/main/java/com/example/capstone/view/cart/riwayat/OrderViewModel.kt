package com.example.capstone.view.cart.riwayat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.api.response.Order
import com.example.capstone.data.repository.ProductRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class OrderViewModel(private val orderRepository: ProductRepository) : ViewModel() {

    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>> = _orders

    fun getOrders() {
        viewModelScope.launch {
            val token = orderRepository.userPreference.getSession().firstOrNull()?.token ?: return@launch
            val response = orderRepository.getOrders(token)
            if (response.isSuccessful) {
                _orders.value = response.body()
            } else {
                // handle error
            }
        }
    }
}