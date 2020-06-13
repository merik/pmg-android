package com.dmc.sw.framework.db

import android.content.Context
import com.dmc.core.data.MovieLocalDataSource
import com.dmc.core.domain.Movie
import com.dmc.core.domain.MovieCharacter
import com.dmc.sw.framework.db.entities.*

class RoomMovieDataSource(context: Context) : MovieLocalDataSource {

    private val movieDao = SwDatabase.getDatabase(context).movieDao()

    override suspend fun getMovies(): List<Movie> = movieDao.getMoviesWithCharacters().map {
        it.toDomainMovie()
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        movieDao.saveMovies(movies)
    }

    override suspend fun getMovieCharacter(url: String): MovieCharacter {
        return movieDao.getMovieCharacter(url)?.toMovieCharacter()
    }

    override suspend fun saveMovieCharacter(character: MovieCharacter) {
        val characterEntity = MovieCharacterEntity.fromDomainMovieCharacter(character)
        movieDao.saveMovieCharacter(characterEntity)
    }

    override suspend fun getMovie(id: Int): Movie {
        val movie = movieDao.getMovieWithCharacters(id)
        return movie.toDomainMovie()
    }


}