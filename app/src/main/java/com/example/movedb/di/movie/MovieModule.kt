package com.example.movedb.di.movie

import com.example.movedb.data.api.MovieAPI
import com.example.movedb.data.repository.MovieRepositoryImpl
import com.example.movedb.data.retrofit.RetrofitManager
import com.example.movedb.data.usecase.movie.GetMovieUseCaseImpl
import com.example.movedb.di.DefaultApiQualifier
import com.example.movedb.domain.repository.MovieRepository
import com.example.movedb.domain.usecase.GetMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieAPI: MovieAPI
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieAPI = movieAPI
        )
    }

    @Singleton
    @Provides
    fun provideMovieAPI(@DefaultApiQualifier retrofitManager: RetrofitManager): MovieAPI {
        return retrofitManager[MovieAPI::class.java]
    }

    @Provides
    fun provideGetMovieUseCase(mMovieRepository: MovieRepository): GetMovieUseCase {
        return GetMovieUseCaseImpl(mMovieRepository)
    }

}