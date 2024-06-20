package com.example.capstone.data.api.services

import com.example.capstone.data.api.response.LoginResponse
import com.example.capstone.data.api.response.RegisterResponse
import com.example.capstone.view.profile.UserProfile
import com.example.capstone.data.api.response.UserProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface AuthApiService {

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ):LoginResponse

    @POST("auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ):RegisterResponse


    @PUT("users/{id}")
    suspend fun updateUserProfile(
        @Path("id") userId: String,
        @Body userProfile: UserProfile
    ): Response<UserProfileResponse>

}
