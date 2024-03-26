package com.damian.healthchef.di

import com.damian.healthchef.common.Constantes.BASE_URL
import com.damian.healthchef.data.api.TheMealDBApi
import com.damian.healthchef.data.repository.MealRepository
import com.damian.healthchef.data.repository.MealRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Este objeto define un módulo Dagger llamado AppModule que proporciona instancias de
 * objetos necesarios para la aplicación, como Retrofit, TheMealDBApi y MealRepository.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Proporciona una instancia de Retrofit configurada con la URL base y el convertidor Gson.
     * Esta instancia se utiliza para realizar solicitudes de red en la aplicación.
     * @return Instancia de Retrofit.
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Proporciona una instancia de TheMealDBApi, que es una interfaz Retrofit que define
     * los puntos de acceso a la API de TheMealDB.
     * @param retrofit Instancia de Retrofit proporcionada por provideRetrofit().
     * @return Instancia de TheMealDBApi.
     */
    @Provides
    @Singleton
    fun providesMealService(retrofit: Retrofit): TheMealDBApi {
        return retrofit.create(TheMealDBApi::class.java)
    }

    /**
     * Proporciona una instancia de MealRepository, que es una interfaz que define métodos
     * para obtener datos relacionados con comidas.
     * @param api Instancia de TheMealDBApi proporcionada por providesMealService().
     * @return Instancia de MealRepository.
     */
    @Provides
    @Singleton
    fun provideMealRepository(api: TheMealDBApi): MealRepository {
        return MealRepositoryImpl(api)
    }
}
