package com.example.jokesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jokesapp.data.local.entity.FavoriteJoke

/**
 * Created by berchoes on 21.01.2022.
 */

@Database(entities = [FavoriteJoke::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract fun getFavoritesDao(): FavoritesDao
}