package com.example.fetch

import retrofit2.HttpException
import retrofit2.Response

sealed interface NetworkResult<T : Any>

class Success<T : Any>(val data: T) : NetworkResult<T>
class Error<T : Any>(val code: Int, val message: String?) : NetworkResult<T>
class Exception<T : Any>(val e: Throwable) : NetworkResult<T>

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            Success(body)
        } else {
            Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        Exception(e)
    }
}