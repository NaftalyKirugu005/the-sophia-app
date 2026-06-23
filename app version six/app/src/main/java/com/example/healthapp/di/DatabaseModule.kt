package com.example.healthapp.di

import android.content.Context
import androidx.room.Room
import com.example.healthapp.data.local.AliyaDatabase
import com.example.healthapp.data.local.dao.HealthDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AliyaDatabase {
        return Room.databaseBuilder(
            context,
            AliyaDatabase::class.java,
            "aliya_db"
        ).build()
    }

    @Provides
    fun provideHealthDao(db: AliyaDatabase): HealthDao {
        return db.healthDao()
    }
}
