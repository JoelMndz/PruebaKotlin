package com.example.pruebakotlin.repository

import com.example.pruebakotlin.model.MovieModel

class MovieRepository(private val movies: MutableList<MovieModel>) {
    fun getMovies() = movies

    fun addMovies(movie: MovieModel) = movies.add(movie)
}