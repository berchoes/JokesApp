package com.example.jokesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by berchoes on 21.01.2022.
 */


@Entity(tableName = "jokes_table")
data class FavoriteJoke(
    @PrimaryKey val id: String,
    val icon: String,
    val content: String
)
