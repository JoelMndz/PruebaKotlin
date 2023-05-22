package com.example.pruebakotlin.ui.movie.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pruebakotlin.MovieReviewerApplication
import com.example.pruebakotlin.model.MovieModel
import com.example.pruebakotlin.repository.MovieRepository

class MovieViewModel (private val repository: MovieRepository): ViewModel(){
    val titulo = MutableLiveData("");
    val director = MutableLiveData("");
    val status = MutableLiveData("");

    fun getMovies() = repository.getMovies()

    private fun addMovies(movie:MovieModel) = repository.addMovies(movie)

    fun createMovie(){
        if(!validateData()){
            status.value = WRONG_INFORMATION;
            return
        }

        addMovies(
            MovieModel(
                titulo.value!!,
                director.value!!
            )
        )
        clearData()

        status.value = MOVIE_CREATED
    }

    private fun validateData():Boolean{
        when{
            titulo.value.isNullOrBlank() -> return false
            director.value.isNullOrBlank() -> return false
        }
        return true
    }

    private fun clearData(){
        titulo.value = ""
        director.value = ""
    }

    fun clearStatus(){
        status.value = INACTIVE
    }

    fun setSelectedMovie(movie: MovieModel){
        titulo.value = movie.titulo;
        director.value = movie.director
    }

    companion object{
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieReviewerApplication
                MovieViewModel(app.movieRepository)
            }
        }

        const val MOVIE_CREATED = "Movie created"
        const val WRONG_INFORMATION = "Wrong information"
        const val INACTIVE = ""
    }

}