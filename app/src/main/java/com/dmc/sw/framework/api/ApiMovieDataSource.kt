package com.dmc.sw.framework.api

import com.dmc.core.data.MovieRemoteDataSource
import com.dmc.core.domain.Movie
import com.dmc.core.domain.MovieCharacter
import com.dmc.sw.framework.api.response.toMovie
import com.dmc.sw.framework.api.response.toMovieCharacter

class ApiMovieDataSource : MovieRemoteDataSource {

    private val apiService = RetrofitFactory.makeRetrofitService()

    override suspend fun getMovies(): List<Movie> {
        val response = apiService.getMovies()
        return response.results.map {
            it.toMovie()
        }

    }

    override suspend fun getMovieCharacter(url: String): MovieCharacter {
        val character = apiService.getCharacter(url)
        return character.toMovieCharacter()

    }
}