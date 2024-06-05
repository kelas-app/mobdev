package com.example.capstone.data.api.response

data class RegisterRequest(
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val phone: String,
    val password: String,
    val address: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class ApiResponse<T>(
    val status: String,
    val message: String,
    val data: T?,
    val token: String?
)

data class RegisterResponse(
    val _id: String,
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val phone: String,
    val address: String
)

data class LoginResponse(
    val _id: String,
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val phone: String,
    val address: String
)
