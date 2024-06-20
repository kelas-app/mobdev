package com.example.capstone.data.repository

import com.example.capstone.data.api.response.Data
import com.example.capstone.data.api.services.AuthApiService
import com.example.capstone.data.api.services.EditProfileApiService
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.view.profile.SellerProfile
import com.example.capstone.view.profile.UserProfile

class EditProfileRepository(
    private val editProfileApiService: EditProfileApiService,
    private val userPreference: UserPreference
) {

    suspend fun updateUserProfile(userId: String, userProfile: UserProfile): Data {
        return editProfileApiService.updateUserProfile(userId, userProfile)
    }
    suspend fun updateSellerProfile(userId: String, userProfile: SellerProfile): Data {
        return editProfileApiService.updateSellerProfile(userId, userProfile)
    }


}
