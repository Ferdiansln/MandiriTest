package com.example.mandiritest.di

import com.example.mandiritest.BuildConfig
import com.example.mandiritest.core.datasource.NewsRemoteDataSource
import com.example.mandiritest.core.repository.NewsRemoteRepository
import com.example.mandiritest.core.repository.NewsRepository
import com.example.mandiritest.core.usecase.NewsUseCase
import com.example.mandiritest.rest.ApiResponseCallAdapterFactory
import com.example.mandiritest.rest.HeadersInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor())
            .addInterceptor(HeadersInterceptor())
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
        return builder.build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun providesNewsRemoteDataSource(retrofit: Retrofit): NewsRemoteDataSource {
        return retrofit.create(NewsRemoteDataSource::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(newsRemoteDataSource: NewsRemoteDataSource): NewsRemoteRepository {
        return NewsRepository(newsRemoteDataSource)
    }

    @Provides
    @Singleton
    fun providesNewsUseCase(newsRemoteRepository: NewsRemoteRepository): NewsUseCase {
        return NewsUseCase(newsRemoteRepository)
    }
}
