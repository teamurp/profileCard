package com.example.practice_1

import kotlinx.coroutines.flow.Flow

class ProfileRepo(
    private val dao: UserDao = AppDatabase.INSTANCE.dao()
){
    val followers: Flow<List<Follower>> = dao.getFollowers()
    suspend fun loadProfile(): UserProfile? = dao.getUser()
    suspend fun refreshFromNetwork() {
        val user = Api.api.getProfile()
        val followers = Api.api.getFollowers()

        dao.saveUser(user)
        dao.insertFollowers(followers)
    }
    suspend fun deleteFollower(follower: Follower) {
        dao.deleteFollower(follower)
    }
    suspend fun insertFollower(followers: List<Follower>) {
        dao.insertFollowers(followers)
    }
}