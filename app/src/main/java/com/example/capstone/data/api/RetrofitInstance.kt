package com.example.capstone.data.api

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

