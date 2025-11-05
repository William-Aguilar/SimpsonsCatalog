package com.example.simpsoncatalog.data.datasource.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SafeApiCall @Inject constructor(
    private val json: Json
) {

    private fun ResponseBody?.parseApiError(defaultCode: Int = NetworkErrorCodes.UNKNOWN): ApiError {
        val text = try {
            this?.string()
        } catch (e: Exception) {
            null
        }
        if (text.isNullOrBlank()) {
            return ApiError(defaultCode, "Empty error body")
        }
        return try {
            json.decodeFromString<ApiError>(text)
        } catch (e: Exception) {
            ApiError(defaultCode, "Failed to parse error body: ${e.localizedMessage ?: "unknown"}")
        }
    }

    internal suspend inline fun <T : Any> safeApiCall(crossinline apiCall: suspend () -> Response<T>): NetworkResult<T> = withContext(Dispatchers.IO) {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) NetworkResult.Success(body)
                else NetworkResult.Error(ApiError(message = "Response body is null"))
            } else {
                val apiError = response.errorBody().parseApiError(response.code())
                NetworkResult.Error(apiError)
            }
        } catch (throwable: Throwable) {
            val apiError = when (throwable) {
                is UnknownHostException -> ApiError(NetworkErrorCodes.NO_INTERNET, "No internet connection")
                is SocketTimeoutException -> ApiError(NetworkErrorCodes.TIMEOUT, "Request timed out")
                else -> ApiError(NetworkErrorCodes.UNKNOWN, throwable.localizedMessage ?: "An unexpected error occurred")
            }
            NetworkResult.Error(apiError)
        }
    }
}

object NetworkErrorCodes {
    const val UNKNOWN = -1000
    const val NO_INTERNET = -1001
    const val TIMEOUT = -1002
}
