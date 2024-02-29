package com.example.movedb.domain.usecase

import com.example.movedb.domain.TaskResult
import com.example.movedb.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface GetMovieUseCase {
    operator fun invoke(): Flow<TaskResult<MovieModel>>
}