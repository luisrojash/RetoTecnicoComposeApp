package com.lerp.networking.remote

class ErrorResponse(
    val code: String?,
    val httpStatus: String?,
    val timestamp: String?,
    val message: String?,
    val debugMessage: String?,
    val subErrors: List<Any>?
)