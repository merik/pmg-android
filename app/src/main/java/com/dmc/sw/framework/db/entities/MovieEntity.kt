package com.dmc.sw.framework.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dmc.core.domain.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "Movie")
data class MovieEntity(
    @PrimaryKey @ColumnInfo(name="movieId")
    val movieId: Int,
    val title: String,
    val director: String,
    val producer: String,
    @ColumnInfo(name="releaseDate")
    val releaseDate: Long,
    val openingCrawl: String) {

    companion object {
        fun fromDomainMovie(movie: Movie): MovieEntity {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.parse(movie.releaseDate, formatter)
            val releaseDate = date.toEpochDay()

            return MovieEntity(
                movieId = movie.episodeId,
                title = movie.title,
                director = movie.director,
                producer = movie.producer,
                releaseDate = releaseDate,
                openingCrawl = movie.openingCrawl
            )
        }
    }

}

//fun MovieEntity.toMovie(): Movie {
//    val formatter = SimpleDateFormat("yyyy-MM-dd")
//    val date = Date(releaseDate)
//
//    val formattedDate =  formatter.format(date)
//
//    return Movie(episodeId = movieId, title = title, director = director, producer = producer, releaseDate = formattedDate)
//}
