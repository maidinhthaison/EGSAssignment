package com.example.movedb.data.api

import com.example.movedb.data.api.model.response.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface MovieAPI {

    @GET("/api/v{version}/user")
    suspend fun getMovies(
    ): Response<MovieResponseDto>

}