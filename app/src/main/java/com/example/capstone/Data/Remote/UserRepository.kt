package com.example.capstone.Data.Remote

class UserRepository {
    suspend fun register(request: RegisterRequest) = RetrofitInstance.api.register(request)
    suspend fun login(request: LoginRequest) = RetrofitInstance.api.login(request)
}

