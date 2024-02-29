package com.example.movedb.data.repository

import com.example.movedb.data.SafeCallAPI
import com.example.movedb.data.api.MovieAPI
import com.example.movedb.domain.TaskResult
import com.example.movedb.domain.map
import com.example.movedb.domain.model.MovieModel
import com.example.movedb.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(private val movieAPI: MovieAPI,
                          private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO) : MovieRepository {
    override fun getMovies(): Flow<TaskResult<MovieModel>> = flow {
        emit(TaskResult.Loading)
        val result = SafeCallAPI.callApi {
            movieAPI.getMovies()
        }.map {
            it.toMovieModel()
        }
        emit(result)
    }.flowOn(defaultDispatcher)
}