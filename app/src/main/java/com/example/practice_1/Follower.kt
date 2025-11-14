package com.example.practice_1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "followers")
data class Follower(
    @PrimaryKey val id: Int,
    val name: String,
    val avatarRes: Int,
    val isFollowing: Boolean
)