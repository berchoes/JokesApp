package com.example.jokesapp.data.local

import androidx.room.*
import com.example.jokesapp.data.local.entity.FavoriteJoke
import kotlinx.coroutines.flow.Flow

/**
 * Created by berchoes on 21.01.2022.
 */

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM jokes_table")
    fun getAllFavorites(): Flow<List<FavoriteJoke>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(joke: FavoriteJoke)

    @Delete
    suspend fun deleteFavorite(joke: FavoriteJoke)

}