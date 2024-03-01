package com.example.movedb.usecase

import com.example.movedb.data.usecase.movie.GetMovieUseCaseImpl
import com.example.movedb.domain.TaskResult
import com.example.movedb.domain.repository.MovieRepository
import com.example.movedb.repository.MovieRepositoryMock
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class GetMovieUseCaseImplTest {
    private lateinit var repository: MovieRepository

    @Before
    fun init() {
        repository = MovieRepositoryMock()
    }


    @Test
    fun `fetch list movie response success`() {
        runBlocking {

            val useCase = GetMovieUseCaseImpl(repository)
            val results = useCase.invoke(version = "3", include_adult = true, include_video = true, language = "en-US",
                page = 1, sort_by = "popularity.desc").take(2).toList()
            Assert.assertEquals(results[0], TaskResult.Loading)
            Assert.assertEquals(results[1], TaskResult.Success(results[1].value()))
        }
    }
    @Test
    fun `fetch list movie response fail`() {
        runBlocking {

            val useCase = GetMovieUseCaseImpl(repository)
            val results = useCase.invoke(version = "3", include_adult = true, include_video = true, language = "en-US",
                page = 1, sort_by = "popularity.desc").take(2).toList()
            Assert.assertEquals(results[0], TaskResult.Loading)
            Assert.assertNotEquals(results[1], results[1].error()?.let { TaskResult.Failure(it) })
        }
    }
}