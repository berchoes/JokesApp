package com.example.jokesapp.di

import com.example.jokesapp.data.remote.JokesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {


    @Singleton
    @Provides
    internal fun provideJokesApi(retrofit: Retrofit): JokesApi =
        retrofit.create(JokesApi::class.java)
}