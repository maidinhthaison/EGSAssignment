package com.example.movedb.domain.usecase

import com.example.movedb.domain.TaskResult
import com.example.movedb.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface GetMovieUseCase {
    operator fun invoke(version: String, include_adult: Boolean, include_video: Boolean,
                        language: String, page: Int, sort_by: String ): Flow<TaskResult<MovieModel>>
}