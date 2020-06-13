package com.dmc.core.interactors

import com.dmc.core.data.MovieRepository

class GetMovie(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(id: Int) = movieRepository.getMovie(id)
}