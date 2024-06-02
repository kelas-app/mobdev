package com.example.capstone.Data.Remote

import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse<RegisterResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<LoginResponse>
}
