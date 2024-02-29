package com.example.movedb.domain.repository

import com.example.movedb.domain.TaskResult
import com.example.movedb.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<TaskResult<MovieModel>>
}