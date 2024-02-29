package com.example.movedb.config

enum class BackendEnvironment (
    val baseUrl: String
) {

    Dev(
    baseUrl = "https://api-dev.klara.tech/"
    ),

    Staging(
    baseUrl = "https://api-dev.klara.tech/"
    ),

    Prod(
    baseUrl = "https://swisspost.com"
    )

}