package com.example.practice_1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_profiles WHERE id = 0")
    suspend fun getUser(): UserProfile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserProfile)

    @Query("SELECT * FROM followers")
    fun getFollowers(): Flow<List<Follower>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFollowers(list: List<Follower>)

    @Delete
    suspend fun deleteFollower(follower: Follower)
}