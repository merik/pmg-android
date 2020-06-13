package com.dmc.sw.framework.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dmc.core.domain.MovieCharacter

@Entity(tableName = "MovieCharacter", indices = [Index("characterId")])
data class MovieCharacterEntity(
    @PrimaryKey @ColumnInfo(name="characterId")
    val url: String,

    val name: String,
    val height: String,
    val mass: String,
    val birth_year: String,
    val gender: String
    ) {

    companion object {
        fun fromDomainMovieCharacter(character: MovieCharacter): MovieCharacterEntity {
            return MovieCharacterEntity(
                url = character.url,
                name = character.name,
                height = character.height,
                mass = character.mass,
                birth_year = character.birth_year,
                gender = character.gender
            )
        }
    }

}

fun MovieCharacterEntity.toMovieCharacter(): MovieCharacter {
    return MovieCharacter(
        url = url,
        name = name,
        height = height,
        mass = mass,
        birth_year = birth_year,
        gender = gender
    )
}
