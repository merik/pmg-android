package com.dmc.sw.framework.db.entities

import androidx.room.Entity

@Entity(tableName = "MovieCharacterCrossRef", primaryKeys = ["movieId", "characterId"])
data class MovieCharacterCrossRef(
    val movieId: Int,
    val characterId: String
)