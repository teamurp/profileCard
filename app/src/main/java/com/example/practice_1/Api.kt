package com.example.practice_1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    val api: MockApi by lazy {
        Retrofit.Builder()
            .baseUrl("jsonplaceholder.typicode.com/users")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MockApi::class.java)
    }
}