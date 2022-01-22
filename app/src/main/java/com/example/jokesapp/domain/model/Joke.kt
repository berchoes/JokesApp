package com.example.jokesapp.domain.model

import com.example.jokesapp.data.local.entity.FavoriteJoke

data class Joke(
    val iconUrl: String,
    val content: String,
    val id: String,
)


fun Joke.toFavoriteJoke() = FavoriteJoke(
    id = this.id,
    icon = this.iconUrl,
    content = this.content
)