package com.dmc.sw.framework.db.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.dmc.core.domain.Movie
import java.text.SimpleDateFormat
import java.util.*

data class MovieWithCharactersEntity(
    @Embedded val movie: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "characterId",
        associateBy = Junction(MovieCharacterCrossRef::class)
    )
    val characters: List<MovieCharacterEntity>
)

fun MovieWithCharactersEntity.toDomainMovie(): Movie {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    val date = Date(movie.releaseDate)
    val formattedDate =  formatter.format(date)

    return Movie(
        episodeId = movie.movieId,
        title = movie.title,
        director = movie.director,
        producer = movie.producer,
        releaseDate = formattedDate,
        openingCrawl = movie.openingCrawl,
        characters = characters.map {
            it.toMovieCharacter()
        }
    )
}