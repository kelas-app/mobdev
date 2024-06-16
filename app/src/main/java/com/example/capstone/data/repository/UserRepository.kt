package com.example.capstone.data.repository

import com.example.capstone.data.api.response.Data
import com.example.capstone.data.api.response.LoginResponse
import com.example.capstone.data.api.response.RegisterResponse
import com.example.capstone.data.api.services.AuthApiService
import com.example.capstone.data.api.services.LoginRequest
import com.example.capstone.data.api.services.RegisterRequest
import com.example.capstone.data.pref.UserPreference

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private var authApiService: AuthApiService,
){

    suspend fun saveSession(data: Data){
        userPreference.saveSession(data)
    }

    suspend fun login(email:String, password:String): LoginResponse {
        val loginRequest = LoginRequest(email, password)
        return authApiService.login(loginRequest)
    }

    suspend fun register(
        firstname:String,
        lastname:String,
        username:String,
        email : String,
        phone:String,
        password: String,
        address:String): RegisterResponse {

        val registerRequest = RegisterRequest(firstname, lastname, username, email , phone, password, address)
        return authApiService.register(registerRequest)
    }

    fun getSession(): kotlinx.coroutines.flow.Flow<Data> {
        return userPreference.getSession()
    }

    suspend fun logout(){
        userPreference.logout()
    }


    companion object{
        @Volatile
        private var instance: UserRepository?= null
        fun getInstance(
            authApiService: AuthApiService,
            userPreference: UserPreference
        ) = UserRepository(userPreference,authApiService)
    }

}




//class UserRepository {
//    suspend fun register(request: RegisterRequest) = RetrofitInstance.api.register(request)
//    suspend fun login(request: LoginRequest) = RetrofitInstance.api.login(request)
//}

