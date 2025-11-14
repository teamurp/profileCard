package com.example.practice_1

import android.app.Application

class ProfileApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        lateinit var instance: ProfileApp
    }
}