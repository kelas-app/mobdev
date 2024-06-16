package com.example.capstone.di

import android.content.Context
import com.example.capstone.data.api.config.ApiConfig
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.pref.dataStore
import com.example.capstone.data.repository.UserRepository

object Injection {

    fun provideUserRepository(context: Context): UserRepository{
        val preference = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getAuthApiService()
        return UserRepository.getInstance(apiService,preference)
    }


}