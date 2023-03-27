package com.example.jokesapp.di

import android.content.Context
import androidx.room.Room
import com.example.jokesapp.common.DATABASE_NAME
import com.example.jokesapp.data.local.FavoritesDao
import com.example.jokesapp.data.local.FavoritesDatabase
import com.example.jokesapp.data.repository.FavoritesRepositoryImpl
import com.example.jokesapp.domain.repository.FavoritesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by berchoes on 21.01.2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    internal fun provideFavoritesDatabase(@ApplicationContext context: Context): FavoritesDatabase =
        Room.databaseBuilder(context, FavoritesDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    internal fun provideFavoritesDao(database: FavoritesDatabase): FavoritesDao =
        database.getFavoritesDao()
}