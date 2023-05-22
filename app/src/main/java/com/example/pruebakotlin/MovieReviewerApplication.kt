package com.example.pruebakotlin

import android.app.Application
import com.example.pruebakotlin.model.movies
import com.example.pruebakotlin.repository.MovieRepository

class MovieReviewerApplication: Application() {
    val movieRepository: MovieRepository by lazy {
        MovieRepository(movies)
    }
}