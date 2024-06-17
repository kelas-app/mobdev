package com.example.capstone.data.api.services

import com.example.capstone.view.profile.UserProfile
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface EditProfileApiService {

    @PUT("users/{userId}")
    suspend fun updateUserProfile(
        @Path("userId") userId: String,
        @Body userProfile: UserProfile
    )
}
