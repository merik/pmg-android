package com.dmc.sw.framework

import com.dmc.core.interactors.GetCharacter
import com.dmc.core.interactors.GetMovie
import com.dmc.core.interactors.GetMovies

data class Interactors(
    val getMovies: GetMovies,
    val getCharacter: GetCharacter,
    val getMovie: GetMovie
)