package com.example.jokesapp.data.remote.dto

import com.example.jokesapp.domain.model.Joke
import com.google.gson.annotations.SerializedName

data class JokeDto(
    val categories: List<Any>,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val url: String,
    val value: String
)

fun JokeDto.toJoke() = Joke(
    iconUrl = this.iconUrl,
    content = this.value,
    id = this.id
)