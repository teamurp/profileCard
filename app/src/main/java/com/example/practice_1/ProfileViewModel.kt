package com.example.practice_1
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class Follower(
    val id: Int,
    val name: String,
    val pfp: Int,
    var isFollowing: Boolean = true
)

class ProfileViewModel : ViewModel() {
    var name = mutableStateOf("Timur P.")
    var bio = mutableStateOf("3rd year CS student at SDU")
    var followers = mutableStateListOf(
        Follower(0, "Diane", R.drawable.default_pfp),
        Follower(1, "John", R.drawable.default_pfp),
        Follower(2, "Finn", R.drawable.default_pfp),
        Follower(3, "Paul", R.drawable.default_pfp)
    )

    fun updateProfile(newName: String, newBio: String) {
        name.value = newName
        bio.value = newBio
    }
    fun removeFollower(follower: Follower) {
        followers.remove(follower)
    }
    fun addFollower(follower: Follower) {
        followers.add(follower)
    }
}