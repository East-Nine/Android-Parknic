package com.eastnine.data.di

import com.eastnine.domain.BuildConfig
import com.eastnine.data.api.Host
import com.eastnine.data.api.deserializer.ParkDeserializer
import com.eastnine.domain.dto.ParkingDto
import com.eastnine.data.factory.NullOnEmptyConverterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkCoreModule {
    @Provides
    @Singleton
    fun provideNullOnEmptyConverterFactory(): NullOnEmptyConverterFactory = NullOnEmptyConverterFactory.create()
    
    @Provides
    @Singleton
    fun provideCallAdapter(): RxJava3CallAdapterFactory = RxJava3CallAdapterFactory
        .createWithScheduler(Schedulers.io())
    
    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .registerTypeAdapter(object: TypeToken<List<ParkingDto>>(){}.type, ParkDeserializer())
            .create()
    
    @Provides
    @Singleton
    fun provideOpinetOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
    
        if(BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addInterceptor(interceptor)
        }
        
        return okHttpClient.build()
    }
    
    @Provides
    @Singleton
    fun provideServerRetrofitBuilder(
        nullOnEmptyConverterFactory: NullOnEmptyConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(Host.HOST_SEOUL)
        .addCallAdapterFactory(rxJava3CallAdapterFactory)
        .addConverterFactory(nullOnEmptyConverterFactory)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
}