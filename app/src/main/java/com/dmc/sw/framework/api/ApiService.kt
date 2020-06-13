package com.dmc.sw.framework.api

import com.dmc.sw.framework.api.response.MovieCharacterDTO
import com.dmc.sw.framework.api.response.MovieResponseDTO
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET("films")
    suspend fun getMovies(): MovieResponseDTO

    @GET
    suspend fun getCharacter(@Url url: String): MovieCharacterDTO
}