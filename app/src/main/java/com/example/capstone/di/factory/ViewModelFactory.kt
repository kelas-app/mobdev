package com.example.capstone.di.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.capstone.data.repository.ProductRepository
import com.example.capstone.data.repository.UserRepository
import com.example.capstone.di.Injection
import com.example.capstone.view.home.HomeViewModel
import com.example.capstone.view.main.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (private val userRepository: UserRepository , private val productRepository: ProductRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userRepository) as T
            }

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(productRepository) as T
            }

            else -> throw IllegalArgumentException("unknown ViewModel Class: " + modelClass.name)
        }
    }

    companion object {
        fun getInstance(context: Context): ViewModelFactory {
            val userRepository = Injection.provideUserRepository(context)
            val productRepository = Injection.provideProductRepository(context)
            return ViewModelFactory (userRepository , productRepository)
        }
    }
}

