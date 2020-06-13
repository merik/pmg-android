package com.dmc.sw.framework

import android.app.Application
import com.dmc.core.data.MovieRepository
import com.dmc.core.interactors.GetCharacter
import com.dmc.core.interactors.GetMovie
import com.dmc.core.interactors.GetMovies
import com.dmc.sw.framework.api.ApiMovieDataSource
import com.dmc.sw.framework.db.RoomMovieDataSource

class SwApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val movieRepository = MovieRepository(RoomMovieDataSource(this), ApiMovieDataSource())

        SwViewModelFactory.inject(this,
            Interactors(
                GetMovies(movieRepository),
                GetCharacter(movieRepository),
                GetMovie(movieRepository)
            )
        )

    }
}