package com.example.movedb.data

import com.example.movedb.data.api.model.response.MovieItem

object GetMovieResponseMock {

    class MockUpdateProfileRequestDTO {

        var page: Int = 1
            private set
        var total_pages: Long = 42726
            private set
        var total_results: Long = 854504
            private set
        var results = listOf(
            MovieItem(
                false,
                "/4woSOUD0equAYzvwhWBHIJDCM88.jpg",
                genre_ids = listOf(28, 27, 53),
                id = 1096197,
                original_language = "en",
                original_title = "No Way Up",
                overview = "Characters from different backgrounds are thrown together when the plane " +
                        "they're travelling on crashes into the Pacific Ocean. A nightmare fight for " +
                        "survival ensues with the air supply running out and dangers creeping in from all sides.",
                popularity = 2757.43f,
                poster_path = "/hu40Uxp9WtpL34jv3zyWLb5zEVY.jpg",
                release_date = "2024-01-18",
                title = "No Way Up",
                video = false,
                vote_average = 5.7f,
                vote_count = 65
            )
        )
            private set

    }

}