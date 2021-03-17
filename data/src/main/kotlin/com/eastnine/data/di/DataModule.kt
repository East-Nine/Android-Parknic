package com.eastnine.data.di

import com.eastnine.data.api.ParkingService
import com.eastnine.data.repository.ParkingRepositoryImpl
import com.eastnine.domain.repository.ParkingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    
    @Singleton
    @Provides
    fun provideParkingRepository(parkingService: ParkingService): ParkingRepository = ParkingRepositoryImpl(parkingService)
}