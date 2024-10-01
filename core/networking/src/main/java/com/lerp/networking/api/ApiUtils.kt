package com.lerp.networking.api

import com.google.gson.Gson
import com.lerp.domain.Failure
import com.lerp.networking.remote.ErrorResponse
import retrofit2.Response
import com.lerp.domain.Result
import java.io.IOException

suspend fun <R> executeApiService(
    api: suspend () -> Response<R>
): Result<R> {
    return executeApiService(api = api, transformInfrastructureToDomain = { it })
}

fun <R, D> transform(response: Response<R>): Result<D> {
    val httpCode: Int = response.code()
    return try {
        val errorResponse: ErrorResponse =
            Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)
        Result.Error(responseToFailure(httpCode, errorResponse))
    } catch (t: Throwable) {
        handleThrowable(t)
    }
}

suspend fun <D, R> executeApiService(
    transformInfrastructureToDomain: (R) -> D,
    api: suspend () -> Response<R>
): Result<D> {
    try {
        val response: Response<R> = api()
        if (response.isSuccessful) {
            val data = response.body()!!
            val domainResponse = transformInfrastructureToDomain.invoke(data)
            return Result.Success(domainResponse)
        }
        return transform(response)
    } catch (t: Throwable) {
        return handleThrowable(t)
    }
}

fun responseToFailure(httpCode: Int, errorResponse: ErrorResponse): Failure {
    val message = errorResponse.message.orEmpty()
    return when (httpCode) {
        400 -> Failure.BadRequest(message = message, any = errorResponse)
        401 -> Failure.AuthorizationRequired(message = message, any = errorResponse)
        403 -> Failure.AccessDeniedOrForbidden(message = message, any = errorResponse)
        404 -> Failure.NotFound(message = message, any = errorResponse)
        //406 -> Failure.NotAcceptable(message = message, any = errorResponse)
        409 -> Failure.Conflict(message = message, any = errorResponse)
        412 -> Failure.PreconditionFailed(message = message, any = errorResponse)
        421 -> Failure.DestinationLocked(message = message, any = errorResponse)
        422 -> Failure.Unprocessable(message = message, any = errorResponse)
        423 -> Failure.Locked(message = message, any = errorResponse)
        429 -> Failure.TooManyRequest(message = message, any = errorResponse)
        500 -> Failure.Internal(message = message, any = errorResponse)
        503 -> Failure.ServiceUnavailable(message = message, any = errorResponse)
        else -> Failure.Others
    }
}

fun <D> handleThrowable(t: Throwable): Result<D> = Result.Error(
    when (t) {
        is IOException -> Failure.NetworkLost
        else -> Failure.Others
    }
)