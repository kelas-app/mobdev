package com.example.capstone.di.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.repository.OrderRepository
import com.example.capstone.data.repository.ProductRepository
import com.example.capstone.data.repository.UserRepository
import com.example.capstone.di.Injection
import com.example.capstone.view.cart.keranjang.KeranjangViewModel
import com.example.capstone.view.cart.riwayat.OrderViewModel
import com.example.capstone.view.chat.ChatViewModel
import com.example.capstone.view.home.DetailProductBaruViewModel
import com.example.capstone.view.home.HomeViewModel
import com.example.capstone.view.main.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (private val userRepository: UserRepository , private val productRepository: ProductRepository, private val userPreference: UserPreference,    private val orderRepository: OrderRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(productRepository, userPreference) as T
            }
            modelClass.isAssignableFrom(DetailProductBaruViewModel::class.java) -> {
                DetailProductBaruViewModel(productRepository,userRepository) as T
            }
            modelClass.isAssignableFrom(ChatViewModel::class.java) -> {
                ChatViewModel(productRepository, userPreference) as T
            }
            modelClass.isAssignableFrom(OrderViewModel::class.java) -> {
                OrderViewModel(productRepository) as T
            }
            modelClass.isAssignableFrom(KeranjangViewModel::class.java) -> {
                KeranjangViewModel(productRepository,orderRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
//        fun getInstance(context: Context): ViewModelFactory {
//            val userRepository = Injection.provideUserRepository(context)
//            val productRepository = Injection.provideProductRepository(context)
//            return ViewModelFactory (userRepository , productRepository)
//        }

        fun getInstance(context: Context): ViewModelFactory{
            return Injection.provideViewModelFactory(context)
        }
    }
}

