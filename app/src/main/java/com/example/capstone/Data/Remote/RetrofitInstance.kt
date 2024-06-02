package com.example.capstone.Data.Remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://161.97.109.65:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}

