package com.example.capstone.di

import android.content.Context
import com.example.capstone.data.api.config.ApiConfig
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.pref.dataStore
import com.example.capstone.data.repository.ProductRepository
import com.example.capstone.data.repository.UserRepository
import kotlinx.coroutines.flow.first
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

}