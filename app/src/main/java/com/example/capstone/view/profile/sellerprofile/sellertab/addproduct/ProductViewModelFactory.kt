package com.example.capstone.view.profile.sellerprofile.sellertab.addproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.data.repository.ProductRepository
import com.example.capstone.view.cart.keranjang.KeranjangViewModel
import com.example.capstone.view.cart.riwayat.OrderViewModel

class ProductViewModelFactory(private val productRepository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(productRepository) as T
        }
        if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            return OrderViewModel(productRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
