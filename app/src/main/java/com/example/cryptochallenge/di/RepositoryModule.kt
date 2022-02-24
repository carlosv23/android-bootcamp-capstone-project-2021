package com.example.cryptochallenge.di

import android.content.Context
import android.content.SharedPreferences
import com.example.cryptochallenge.data.datasource.remote.RemoteBitsoDataSource
import com.example.cryptochallenge.data.datasource.remote.RemoteBitsoDataSourceImpl
import com.example.cryptochallenge.data.datasource.room.RoomDataSource
import com.example.cryptochallenge.data.datasource.room.RoomDataSourceImpl
import com.example.cryptochallenge.data.preferences.PrefRepository
import com.example.cryptochallenge.data.repository.BitsoRepository
import com.example.cryptochallenge.data.repository.BitsoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun getRemoteBitsoDataSource(remoteBitsoDataSource: RemoteBitsoDataSourceImpl): RemoteBitsoDataSource

    @Binds
    fun getRoomDataSource(roomDataSource: RoomDataSourceImpl): RoomDataSource

    @Binds
    fun getBitsoRepository(bitsoRepository: BitsoRepositoryImpl): BitsoRepository

}