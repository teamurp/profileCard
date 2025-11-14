package com.example.practice_1

import retrofit2.http.GET

interface MockApi {
    @GET("profile")
    suspend fun getProfile(): UserProfile

    @GET("followers")
    suspend fun getFollowers(): List<Follower>
}