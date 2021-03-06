package com.eastnine.domain.di

import com.eastnine.domain.repository.KakaoRepository
import com.eastnine.domain.repository.ParkingRepository
import com.eastnine.domain.usecase.KakaoUseCase
import com.eastnine.domain.usecase.ParkingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideKakaoUseCase(kakaoRepository: KakaoRepository): KakaoUseCase = KakaoUseCase(kakaoRepository)

    @Singleton
    @Provides
    fun provideParkingUseCase(parkingRepository: ParkingRepository): ParkingUseCase = ParkingUseCase(parkingRepository)
}