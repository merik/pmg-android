package com.dmc.core.domain

data class Movie(
    val episodeId: Int,
    val title: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val openingCrawl: String,
    val characters: List<MovieCharacter>
)