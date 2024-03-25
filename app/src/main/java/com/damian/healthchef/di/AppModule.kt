package com.damian.healthchef.di

import com.damian.healthchef.common.Constantes.BASE_URL
import com.damian.healthchef.data.api.MealApiService
import com.damian.healthchef.data.repository.MealRepository
import com.damian.healthchef.data.repository.MealRepositoryImpl
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object AppModule {

    // API
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesMealService(retrofit: Retrofit): MealApiService {
        return retrofit.create(MealApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMealRepository(api: MealApiService): MealRepository {
        return MealRepositoryImpl(api)
    }
}