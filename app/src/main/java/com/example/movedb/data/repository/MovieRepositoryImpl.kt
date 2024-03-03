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
import retrofit2.http.Path
import retrofit2.http.Query

class MovieRepositoryImpl(private val movieAPI: MovieAPI,
                          private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO) : MovieRepository {
    override fun getMovies(include_adult: Boolean, include_video: Boolean,
                           language: String, page: Int, sort_by: String )
    : Flow<TaskResult<MovieModel>> = flow {
        emit(TaskResult.Loading)
        val result = SafeCallAPI.callApi {
            movieAPI.getMovies(include_adult = include_adult, include_video = include_video,
                language = language, page = page, sort_by = sort_by)
        }.map {
            it.toMovieModel()
        }
        emit(result)
    }.flowOn(defaultDispatcher)

}