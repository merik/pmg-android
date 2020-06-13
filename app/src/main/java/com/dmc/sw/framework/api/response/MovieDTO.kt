package com.dmc.sw.framework.api.response

import com.dmc.core.domain.Movie
import com.dmc.core.domain.MovieCharacter
import com.squareup.moshi.Json

data class MovieDTO(
    @field:Json(name="episode_id") val episodeId: Int,
    val title: String,
    val director: String,
    val producer: String,
    @field:Json(name="release_date") val releaseDate: String,
    @field:Json(name="opening_crawl") val openingCrawl: String,
    val characters: List<String>

)

fun MovieDTO.toMovie(): Movie {

    val charactersArray = characters.map {
        MovieCharacter("", "", "", "", "", it)
    }

    return Movie(episodeId = episodeId, title = title, director = director, producer = producer, releaseDate = releaseDate, openingCrawl = openingCrawl, characters = charactersArray)
}