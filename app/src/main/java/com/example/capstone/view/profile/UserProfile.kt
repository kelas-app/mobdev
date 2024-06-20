package com.example.capstone.view.profile

data class UserProfile(
    val firstname: String,
    val lastname: String,
    val username: String,
    val phone: String,
    val address: String
)

data class SellerProfile(
    val role: String,
    val nik: String
)
