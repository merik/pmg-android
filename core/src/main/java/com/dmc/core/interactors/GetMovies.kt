package com.dmc.core.interactors

import com.dmc.core.data.MovieRepository

class GetMovies(private val movieRepository: MovieRepository) {
    suspend operator fun invoke() = movieRepository.getMovies()
}