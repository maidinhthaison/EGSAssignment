package com.example.movedb.config

object AppConfig {
    /**
     * Get environment from current build type
     */
    var backendEnvironment: BackendEnvironment = BackendEnvironment.Dev
    const val jwtToken : String = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNWJhZDQwMDkzMzBkYjg4YTA3YjYyYjEwZTkxNzFlYSIsInN1YiI6IjY1ZTA1OGIwNTI5NGU3MDE2MzRlYTQ4ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QqlyfqCRjGD99wXHXgw0Ap2YW0rOfVRwUHX84-mvRqw"
    private fun getFromBuildType(flavor: String): BackendEnvironment {
        return when (flavor) {
            "dev" -> BackendEnvironment.Dev
            "stag" -> BackendEnvironment.Staging
            else -> BackendEnvironment.Prod
        }
    }

    fun setBackendEnvironment(flavor: String) {
        backendEnvironment = getFromBuildType(flavor)
    }
}