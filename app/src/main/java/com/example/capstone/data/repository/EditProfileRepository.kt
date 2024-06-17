package com.example.capstone.data.repository

import com.example.capstone.data.api.services.AuthApiService
import com.example.capstone.data.api.services.EditProfileApiService
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.view.profile.UserProfile

class EditProfileRepository(
    private val editProfileApiService: EditProfileApiService,
    private val userPreference: UserPreference
) {

    suspend fun updateUserProfile(userId: String, userProfile: UserProfile) {
        // Di sini Anda melakukan pemanggilan ke metode update di ApiService
        // Pastikan Anda mengatur token atau otorisasi yang diperlukan dalam interceptor Retrofit
        editProfileApiService.updateUserProfile(userId, userProfile)
    }


}
