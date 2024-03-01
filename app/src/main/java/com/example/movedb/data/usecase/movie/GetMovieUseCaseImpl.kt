package com.example.movedb.data.usecase.movie

import com.example.movedb.domain.TaskResult
import com.example.movedb.domain.model.MovieModel
import com.example.movedb.domain.repository.MovieRepository
import com.example.movedb.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.flow.Flow

class GetMovieUseCaseImpl (private val movieRepository: MovieRepository) :
    GetMovieUseCase {

    override fun invoke(
        version: String,
        include_adult: Boolean,
        include_video: Boolean,
        language: String,
        page: Int,
        sort_by: String
    ): Flow<TaskResult<MovieModel>> {
        return movieRepository.getMovies(version = version, include_adult = include_adult,
            include_video =  include_video, language = language , page =  page, sort_by = sort_by)
    }
}