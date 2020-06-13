package com.dmc.core.data

import com.dmc.core.domain.Movie
import com.dmc.core.domain.MovieCharacter

class MovieRepository(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource) {

    suspend fun getMovie(id: Int): Movie = movieLocalDataSource.getMovie(id)

    suspend fun getMovies(): List<Movie> {
        val movies = getCacheMovies()
        if (movies.isEmpty()) {
            return refreshMovies()
        } else {
            return movies
        }
    }

    suspend fun getCacheMovies(): List<Movie> = movieLocalDataSource.getMovies()

    suspend fun refreshMovies(): List<Movie> {
        val movies = movieRemoteDataSource.getMovies()
        movieLocalDataSource.saveMovies(movies)
        return getCacheMovies()
    }


    suspend fun getCharacter(url: String): MovieCharacter? {
        var character = movieLocalDataSource.getMovieCharacter(url)
        if (character != null) {
            return character
        }
        character = movieRemoteDataSource.getMovieCharacter(url)

        if (character != null) {
            movieLocalDataSource.saveMovieCharacter(character)
        }

        return movieLocalDataSource.getMovieCharacter(url)

    }

}