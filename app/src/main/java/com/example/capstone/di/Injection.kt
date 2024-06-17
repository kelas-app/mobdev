package com.example.capstone.di

import android.content.Context
import com.example.capstone.data.api.config.ApiConfig
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.pref.dataStore
import com.example.capstone.data.repository.EditProfileRepository
import com.example.capstone.data.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

object Injection {

    fun provideUserRepository(context: Context): UserRepository{
        val preference = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getAuthApiService()
        return UserRepository.getInstance(apiService,preference)
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