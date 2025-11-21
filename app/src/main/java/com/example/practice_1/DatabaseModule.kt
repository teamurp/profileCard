package com.example.practice_1

import android.app.Application

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "profile_db"
        ).build()

    @Provides
    fun provideDao(db: AppDatabase): ProfileDao = db.dao()
}