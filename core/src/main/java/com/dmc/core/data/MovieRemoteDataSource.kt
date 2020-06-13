package com.dmc.core.data

import com.dmc.core.domain.Movie
import com.dmc.core.domain.MovieCharacter

interface MovieRemoteDataSource {

    suspend fun getMovies(): List<Movie>
    suspend fun getMovieCharacter(url: String): MovieCharacter
}