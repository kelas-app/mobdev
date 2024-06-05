package com.example.capstone.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.example.capstone.data.api.services.UserApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

//object RetrofitInstance {
//
//    private val loggingInterceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//
//    private val client = OkHttpClient.Builder()
//        .addInterceptor(loggingInterceptor)
//        .build()
//
//    private val retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(BuildConfig.API_BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//    }
//
//    val api: UserApiService by lazy {
//        retrofit.create(UserApiService::class.java)
//    }
//}

