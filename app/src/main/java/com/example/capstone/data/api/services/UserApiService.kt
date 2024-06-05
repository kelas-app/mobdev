package com.example.capstone.data.api.services

import com.example.capstone.data.api.response.ApiResponse
import com.example.capstone.data.api.response.LoginRequest
import com.example.capstone.data.api.response.LoginResponse
import com.example.capstone.data.api.response.RegisterRequest
import com.example.capstone.data.api.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {
    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse<RegisterResponse>

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<LoginResponse>
}
