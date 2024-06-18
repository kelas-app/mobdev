package com.example.capstone.view.cart

import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {

    private val _cartItems = mutableListOf<CartItem>()
    val cartItems: List<CartItem>
        get() = _cartItems

    private var _totalPrice = 0.0
    val totalPrice: Double
        get() = _totalPrice

    fun addToCart(item: CartItem) {
        _cartItems.add(item)
        _totalPrice += item.price
        // Update other necessary data or perform any additional logic
    }

    fun removeFromCart(item: CartItem) {
        if (_cartItems.remove(item)) {
            _totalPrice -= item.price
            // Update other necessary data or perform any additional logic
        }
    }

    // Add more methods as needed, such as updating item quantity, clearing cart, etc.
}

data class CartItem(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int
)
