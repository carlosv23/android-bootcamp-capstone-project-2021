package com.example.cryptochallenge.di

import android.util.Log
import com.example.cryptochallenge.BuildConfig
import com.example.cryptochallenge.data.service.BitsoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.internal.addHeaderLenient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import java.net.HttpCookie
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
        client(getHttpClient())
    }
        .build()
        .create(BitsoService::class.java)

    @Singleton
    @Provides
    fun getInterceptor(): Interceptor = object: Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val request2 = request.newBuilder().addHeader("User-Agent", "MyBitsoApp").build()
            Log.d(
                "OkHttp", "--> Sending request ${request2.url} with header: ${request2.headers}"
            )
            return chain.proceed(request2)
        }
    }

    @Singleton
    @Provides
    fun getOkHttpInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    @Singleton
    @Provides
    fun getHttpClient() = OkHttpClient.Builder()
        .addInterceptor(getOkHttpInterceptor())
        .addInterceptor(getInterceptor())
        .build()
}
