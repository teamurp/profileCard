package com.example.practice_1
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow
import kotlin.getValue
import androidx.room.Room
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

//data class Follower(
//    val id: Int,
//    val name: String,
//    val pfp: Int,
//    var isFollowing: Boolean = true
//)

//class ProfileViewModel : ViewModel() {
//    var name = mutableStateOf("Timur P.")
//    var bio = mutableStateOf("3rd year CS student at SDU")
//    var followers = mutableStateListOf(
//        Follower(0, "Diane", R.drawable.default_pfp),
//        Follower(1, "John", R.drawable.default_pfp),
//        Follower(2, "Finn", R.drawable.default_pfp),
//        Follower(3, "Paul", R.drawable.default_pfp)
//    )
//
//    fun updateProfile(newName: String, newBio: String) {
//        name.value = newName
//        bio.value = newBio
//    }
//    fun removeFollower(follower: Follower) {
//        followers.remove(follower)
//    }
//    fun addFollower(follower: Follower) {
//        followers.add(follower)
//    }
//}

class ProfileViewModel(
    private val repo: ProfileRepo = ProfileRepo()
) : ViewModel(){
    var user by mutableStateOf<UserProfile?>(null)
    val followers = repo.followers.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        emptyList()
    )
    init {
        viewModelScope.launch {
            user = repo.loadProfile()
        }
    }
    fun refresh() {
        viewModelScope.launch {
            repo.refreshFromNetwork()
            user = repo.loadProfile()
        }
    }
    fun deleteFollower(follower: Follower) {
        viewModelScope.launch {
            repo.deleteFollower(follower)
        }
    }
    fun insertFollowers(followers: List<Follower>) {
        viewModelScope.launch {
            repo.insertFollower(followers)
        }
    }
}