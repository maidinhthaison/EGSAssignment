package com.example.movedb.data.api

import com.example.movedb.config.API_VERSION
import com.example.movedb.data.api.model.response.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("/{version}/discover/movie")
    suspend fun getMovies(
        @Path("version") version: String = API_VERSION,
        @Query("include_adult") include_adult: Boolean?,
        @Query("include_video") include_video: Boolean?,
        @Query("language") language: String?,
        @Query("page") page: Int?,
        @Query("sort_by") sort_by: String?
    ): Response<MovieResponseDto>

}