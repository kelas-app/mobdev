package com.example.capstone.data.api.services

import com.example.capstone.data.api.response.LoginResponse
import com.example.capstone.data.api.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ):LoginResponse

    @POST("auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ):RegisterResponse



//    @FormUrlEncoded
//    @POST("auth/login")
//    suspend fun login(
//        @Field ("email") email : String,
//        @Field ("password") password : String
//    ):LoginResponse
//    @FormUrlEncoded
//    @POST("auth/register")
//    suspend fun register(
//        @Field ("firstname") firstname: String,
//        @Field ("lastname") lastname: String,
//        @Field ("username") username: String,
//        @Field ("email") email: String,
//        @Field ("phone") phone: String,
//        @Field ("password") password: String,
//        @Field ("address") address: String
//    ):RegisterResponse


}
