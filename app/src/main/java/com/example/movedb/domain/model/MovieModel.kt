package com.example.movedb.domain.model

import com.example.movedb.data.api.model.response.MovieItem
import java.io.Serializable

data class MovieModel(

    val page: Int? = null,
    val total_pages: Long? = null,
    val results: List<MovieItem>? = null,
    val total_results: Long? = null

) : Serializable
