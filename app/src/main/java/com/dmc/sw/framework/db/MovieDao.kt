package com.dmc.sw.framework.db

import androidx.room.*
import com.dmc.core.domain.Movie
import com.dmc.sw.framework.db.entities.MovieCharacterCrossRef
import com.dmc.sw.framework.db.entities.MovieCharacterEntity
import com.dmc.sw.framework.db.entities.MovieEntity
import com.dmc.sw.framework.db.entities.MovieWithCharactersEntity


@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie ORDER BY releaseDate")
    suspend fun getMoviesWithCharacters(): List<MovieWithCharactersEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun newMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun newMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieCharacter(character: MovieCharacterEntity)

    @Transaction
    @Query("SELECT * FROM Movie WHERE movieId = :movieId")
    suspend fun getMovieWithCharacters(movieId: Int): MovieWithCharactersEntity

    @Query("SELECT * FROM MovieCharacter WHERE characterId = :characterId")
    suspend fun getMovieCharacter(characterId: String): MovieCharacterEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieCharacterRef(ref: MovieCharacterCrossRef)

    @Transaction
    suspend fun saveMovieWithCharacters(movie: MovieEntity, characters: List<MovieCharacterEntity>) {
        newMovie(movie)
        for (character in characters) {
            saveMovieCharacter(character)
            saveMovieCharacterRef(MovieCharacterCrossRef(movieId = movie.movieId, characterId = character.url))
        }

    }

    @Transaction
    suspend fun saveMovies(movies: List<Movie>) {
        for (movie in movies) {
            val characters = movie.characters.map { MovieCharacterEntity.fromDomainMovieCharacter(it) }
            saveMovieWithCharacters(MovieEntity.fromDomainMovie(movie), characters)
        }
    }
}