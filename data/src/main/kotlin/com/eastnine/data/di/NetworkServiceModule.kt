package com.eastnine.data.di

import com.eastnine.data.api.Host
import com.eastnine.data.api.service.KakaoService
import com.eastnine.data.api.service.ParkingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServiceModule {

    @Singleton
    @Provides
    fun provideKakaoService(@Named(Host.HOST_KAKAO) retrofitBuilder: Retrofit): KakaoService =
        retrofitBuilder.create(KakaoService::class.java)

    @Singleton
    @Provides
    fun provideParkingService(@Named(Host.HOST_SEOUL) retrofitBuilder: Retrofit): ParkingService =
        retrofitBuilder.create(ParkingService::class.java)
}