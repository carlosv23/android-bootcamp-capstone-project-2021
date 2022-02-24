package com.example.cryptochallenge.di

import android.content.Context
import androidx.room.Room
import com.example.cryptochallenge.data.db.AppDatabase
import com.example.cryptochallenge.data.preferences.PrefRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomClient {

    @Singleton
    @Provides
    fun getDB(@ApplicationContext appContext: Context): AppDatabase = synchronized(this){
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "hero_db"
        )
            .build()
    }

    @Singleton
    @Provides
    fun getBookDao(appDatabase: AppDatabase) = appDatabase.getBookDao()

    @Singleton
    @Provides
    fun geOrderkDao(appDatabase: AppDatabase) = appDatabase.getOrderDao()

    @Singleton
    @Provides
    fun getTickerDataDao(appDatabase: AppDatabase) = appDatabase.getTickerDataDao()

}
