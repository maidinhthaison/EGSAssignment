package com.example.movedb.data.usecase.movie

import com.example.movedb.domain.TaskResult
import com.example.movedb.domain.model.MovieModel
import com.example.movedb.domain.repository.MovieRepository
import com.example.movedb.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.flow.Flow

class GetMovieUseCaseImpl (private val movieRepository: MovieRepository) :
    GetMovieUseCase {

    override fun invoke(): Flow<TaskResult<MovieModel>> {
        return movieRepository.getMovies()
    }
}