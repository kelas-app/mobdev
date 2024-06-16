package com.example.capstone.data.api.services

data class RegisterRequest(
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val phone: String,
    val password: String,
    val address: String
)