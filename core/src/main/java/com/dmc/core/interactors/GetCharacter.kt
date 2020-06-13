package com.dmc.core.interactors

import com.dmc.core.data.MovieRepository

class GetCharacter(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(url: String) = movieRepository.getCharacter(url)
}