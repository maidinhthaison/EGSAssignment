package com.example.movedb.data.api.model.response

import com.example.movedb.domain.model.MovieModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MovieResponseDto (
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String
): Serializable {
    fun toMovieModel(): MovieModel {
        return MovieModel(
            firstName = firstName,
            lastName = lastName
        )

    }
}