package com.example.cryptochallenge.di

import com.example.cryptochallenge.data.datasource.remote.RemoteBitsoDataSource
import com.example.cryptochallenge.data.datasource.remote.RemoteBitsoDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun getRemoteBitsoDataSource(remoteBitsoDataSource: RemoteBitsoDataSourceImpl): RemoteBitsoDataSource

}