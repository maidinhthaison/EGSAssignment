package com.example.movedb.repository

import com.example.movedb.data.GetMovieResponseMock
import com.example.movedb.domain.AppError
import com.example.movedb.domain.TaskResult
import com.example.movedb.domain.model.MovieModel
import com.example.movedb.domain.repository.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryMock : MovieRepository{
    private var isError = false
    private var movieModel: MovieModel
    private var movieModelMock : GetMovieResponseMock.MockUpdateProfileRequestDTO = GetMovieResponseMock.MockUpdateProfileRequestDTO()
    init {
        movieModel = MovieModel(movieModelMock.page, movieModelMock.total_pages, movieModelMock.results,
            movieModelMock.total_results)
    }
    override fun getMovies(
        version: String,
        include_adult: Boolean,
        include_video: Boolean,
        language: String,
        page: Int,
        sort_by: String
    ): Flow<TaskResult<MovieModel>> {
        return flow {
            emit(TaskResult.Loading)

            delay(2000L)

            if (isError) {
                emit(TaskResult.Failure(AppError.GeneralError(NullPointerException())))
            } else {
                emit(TaskResult.Success(movieModel))
            }
        }
    }
}