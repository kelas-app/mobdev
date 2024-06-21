package com.example.capstone.di

import android.content.Context
import com.example.capstone.data.api.config.ApiConfig
import com.example.capstone.data.api.services.CartRequest
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.pref.dataStore
import com.example.capstone.data.repository.ProductRepository
import com.example.capstone.data.repository.UserRepository
import com.example.capstone.data.repository.EditProfileRepository
import com.example.capstone.data.repository.OrderRepository
import com.example.capstone.di.factory.ViewModelFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

object Injection {

    fun provideUserRepository(context: Context): UserRepository{
        val preference = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getAuthApiService()
        return UserRepository.getInstance(apiService,preference)
    }

    fun provideProductRepository(context: Context):ProductRepository{
        val preference = UserPreference.getInstance(context.dataStore)
        val user = runBlocking {preference.getSession().first()}
        val apiService = ApiConfig.getAllProductService(user.token)
        return ProductRepository.getInstance(preference, apiService)
    }


    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val userRepository = provideUserRepository(context)
        val productRepository = provideProductRepository(context)
        val userPreference = UserPreference.getInstance(context.dataStore)
        val orderRepository = provideOrderRepository(context)
        return ViewModelFactory(userRepository, productRepository, userPreference, orderRepository)
    }

    fun provideOrderRepository(context: Context): OrderRepository { // Update return type to OrderRepository
        val preference = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { preference.getSession().first() }
        val apiService = ApiConfig.getOrderApiService(user.token)
        return OrderRepository.getInstance(apiService)
    }
    fun provideEditProfileRepository(context: Context): EditProfileRepository {
        val userPreference = UserPreference.getInstance(context.dataStore)
        val token = runBlocking {
            userPreference.getSession().firstOrNull()?.token ?: ""
        }
        val apiService = ApiConfig.getEditProfileApiService(token) // Menggunakan ApiConfig baru untuk edit profil
        return EditProfileRepository(apiService, userPreference)
    }


  

}