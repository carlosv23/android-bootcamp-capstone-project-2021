package com.example.cryptochallenge.di

import android.content.Context
import com.example.cryptochallenge.data.preferences.PrefRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    //Shared Preferences Repository
    @Singleton
    @Provides
    fun getSharedRepository(@ApplicationContext appContext: Context) = PrefRepository(appContext)
}
