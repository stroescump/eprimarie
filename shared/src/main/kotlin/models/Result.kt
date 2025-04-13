package models

import kotlinx.serialization.Serializable

@Serializable
sealed class ApiResult<T>{
    @Serializable
    data class Success<T>(val data: T) : ApiResult<T>()

    @Serializable
    data class Failure(val exception: String, val message: String?) : ApiResult<Nothing>()
}

