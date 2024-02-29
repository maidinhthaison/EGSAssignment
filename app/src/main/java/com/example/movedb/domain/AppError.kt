package com.example.movedb.domain

sealed class AppError{
    //API response error
    class ResponseError(
        val errorCode: String? = null,
        val message: String = "Response Error",
        val errorId: String? = null
    ) :
        AppError()

    class NetworkError(val httpCode: Int? = null, val message: String = "Network Error") :
        AppError()

    class ServerError(val message: String = "Server internal error") : AppError()

    // For other error
    class GeneralError(val throwable: Throwable) : AppError()

    fun getMessagegetMessage(): String {
        return when (this) {
            is ResponseError -> message
            is NetworkError -> message
            is ServerError -> message
            is GeneralError -> throwable.message ?: "Unknown error"
        }
    }
}
class ValidationException(message: String) : Exception(message)
