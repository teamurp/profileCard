package com.example.practice_1

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.getValue

@Database(
    entities = [UserProfile::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): UserDao
    companion object {
        val INSTANCE by lazy {
            Room.databaseBuilder(
                ProfileApp.instance,
                AppDatabase::class.java,
                "profile_db"
            ).build()
        }
    }
}