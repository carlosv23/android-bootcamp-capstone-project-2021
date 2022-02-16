package com.example.cryptochallenge.di

import com.example.cryptochallenge.BuildConfig
import com.example.cryptochallenge.data.service.BitsoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkClient {

    @Singleton
    @Provides
    fun getRetrofit(): BitsoService = Retrofit.Builder().apply {
        baseUrl(BuildConfig.BASE_URL)
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        addConverterFactory(GsonConverterFactory.create())
    }.build().create(BitsoService::class.java)


}