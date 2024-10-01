package com.lerp.domain

sealed class Failure {
    data class BadRequest(val message: String, val any: Any? = null) : Failure()
    data class AuthorizationRequired(val message: String, val any: Any? = null) : Failure()
    data class AccessDeniedOrForbidden( val message: String, val any: Any? = null) : Failure()
    data class NotFound(val message: String, val any: Any? = null) : Failure()
    data class Conflict(val message: String, val any: Any? = null) : Failure()
    data class Limits(val code : String, val message: String, val any: Any? = null) : Failure()
    data class DestinationLocked(val message: String, val any: Any? = null) : Failure()
    data class Unprocessable(val message: String, val any: Any? = null) : Failure()
    data class Locked(val message: String, val any: Any? = null) : Failure()
    data class TooManyRequest(val message: String, val any: Any? = null) : Failure()
    data class Internal(val message: String, val any: Any? = null) : Failure()
    data class ServiceUnavailable(val message: String, val any: Any? = null) : Failure()
    data class PreconditionFailed(val message: String, val any: Any? = null) : Failure()
    data class OtpRequired(val message: String, val any: Any? = null,val subErrors: List<Any>?) : Failure()
    object NetworkLost : Failure()
    object Others : Failure()
}

fun findFailure(httpCode: Int, message: String,code: String,subErrors: List<Any>?): Failure {
    return when (httpCode) {
        400 ->  Failure.BadRequest(message)
        401 -> Failure.AuthorizationRequired(message)
        403 -> Failure.AccessDeniedOrForbidden(code, message)
        404 -> Failure.NotFound(message)
        409 -> Failure.Conflict(code, message)
        412 -> Failure.PreconditionFailed(message)
        421 -> Failure.DestinationLocked(message)
        422 -> Failure.Unprocessable(message)
        423 -> Failure.Locked(message)
        428 -> Failure.Limits(code, message, subErrors)
        429 -> Failure.TooManyRequest(message)
        500 -> Failure.Internal(message)
        503 -> Failure.ServiceUnavailable(message)
        else -> Failure.Others
    }
}