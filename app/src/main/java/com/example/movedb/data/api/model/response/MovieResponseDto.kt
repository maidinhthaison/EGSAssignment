package com.example.movedb.data.api.model.response

import com.example.movedb.domain.model.MovieModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieResponseDto (
    @SerializedName("page") val page: Int?,
    @SerializedName("total_pages") val total_pages: Long?,
    @SerializedName("results") val results: List<MovieItem>?,
    @SerializedName("total_results") val total_results: Long?
): Serializable {
    fun toMovieModel(): MovieModel {
        return MovieModel(
            page = page,
            total_pages = total_pages,
            results = results,
            total_results = total_results
        )

    }
}

data class MovieItem(
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("backdrop_path") val backdrop_path: String?,
    @SerializedName("genre_ids") val genre_ids: List<Long>?,
    @SerializedName("id") val id: Long?,
    @SerializedName("original_language") val original_language: String?,
    @SerializedName("original_title") val original_title: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: Float?,
    @SerializedName("poster_path") val poster_path: String?,
    @SerializedName("release_date") val release_date: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("video") val video: Boolean?,
    @SerializedName("vote_average") val vote_average: Float?,
    @SerializedName("vote_count") val vote_count: Int?
) : Serializable

const val PATH_IMAGE_URL: String  = "https://image.tmdb.org/t/p/w500"
