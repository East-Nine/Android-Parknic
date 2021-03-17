package com.eastnine.data.di

import com.eastnine.data.api.ParkingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServiceModule {
    
    @Singleton
    @Provides
    fun provideParkingService(retrofitBuilder: Retrofit): ParkingService =
        retrofitBuilder.create(ParkingService::class.java)
}