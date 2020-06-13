package com.dmc.sw.framework.api.response

import com.dmc.core.domain.MovieCharacter

data class MovieCharacterDTO(
    val name: String,
    val height: String,
    val mass: String,
    val birth_year: String,
    val gender: String,
    val url: String
)

fun MovieCharacterDTO.toMovieCharacter(): MovieCharacter {
    return MovieCharacter(
        name = name,
        height =  height,
        mass =  mass,
        birth_year = birth_year,
        gender = gender,
        url =  url
    )
}