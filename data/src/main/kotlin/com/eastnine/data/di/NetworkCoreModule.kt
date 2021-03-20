package com.eastnine.data.di

import android.content.Context
import android.os.Build
import com.eastnine.domain.BuildConfig
import com.eastnine.data.api.Host
import com.eastnine.data.factory.NullOnEmptyConverterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
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
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    @Named(Host.HOST_KAKAO)
    fun provideKakaoOkHttpClient(): OkHttpClient {

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(object: Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    return chain.proceed(chain
                        .request()
                        .newBuilder()
                        .addHeader("Authorization", "KakaoAK dfbbeadf6d5004e5a4c2a6c34ecd452a")
                        .build()
                    )
                }
            })

        if(BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    @Named(Host.HOST_SEOUL)
    fun provideSeoulOkHttpClient(): OkHttpClient {
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
    @Named(Host.HOST_KAKAO)
    fun provideKakaoServerRetrofitBuilder(
        nullOnEmptyConverterFactory: NullOnEmptyConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        gson: Gson,
        @Named(Host.HOST_KAKAO) okHttpClient: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(Host.HOST_KAKAO)
        .addCallAdapterFactory(rxJava3CallAdapterFactory)
        .addConverterFactory(nullOnEmptyConverterFactory)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    @Named(Host.HOST_SEOUL)
    fun provideSeoulServerRetrofitBuilder(
        nullOnEmptyConverterFactory: NullOnEmptyConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        gson: Gson,
        @Named(Host.HOST_SEOUL) okHttpClient: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(Host.HOST_SEOUL)
        .addCallAdapterFactory(rxJava3CallAdapterFactory)
        .addConverterFactory(nullOnEmptyConverterFactory)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
}