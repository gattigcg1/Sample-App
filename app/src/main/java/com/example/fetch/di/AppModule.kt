package com.example.fetch.di

import com.example.fetch.data.repository.FetchRepository
import com.example.fetch.data.service.FetchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFetchService(retrofit: Retrofit): FetchService {
        return retrofit.create(FetchService::class.java)
    }

    @Provides
    @Singleton
    fun provideFetchRepository(fetchService: FetchService): FetchRepository {
        return FetchRepository(fetchService)
    }
}