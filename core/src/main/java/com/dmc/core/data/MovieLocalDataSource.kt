package com.dmc.core.data

import com.dmc.core.domain.Movie
import com.dmc.core.domain.MovieCharacter

interface MovieLocalDataSource {
    suspend fun getMovies(): List<Movie>

    suspend fun saveMovies(movies: List<Movie>)

    suspend fun getMovieCharacter(url: String): MovieCharacter

    suspend fun saveMovieCharacter(character: MovieCharacter)

    suspend fun getMovie(id: Int): Movie
}