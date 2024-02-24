package util

import co.touchlab.kermit.Logger
import data.Result
import data.models.entity.BaseModel

/**
 * A generic class that holds a value with its loading status.
 */
sealed class Resource<out T : Any> {
    class Success<out T : Any>(val data: T) : Resource<T>()
    class Error(val message: String, val error: Throwable? = null) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    object Empty : Resource<Nothing>()

    fun status(): String {
        return when (this) {
            is Success -> { "Status: Success" }
            is Error -> { "Status: Error" }
            is Loading -> { "Status: Loading" }
            is Empty -> { "Status: Empty" }
        }
    }
}

fun <T : Any> T?.successOrEmpty(): Resource<T> {
    return when (this) {
        is List<*> -> if (this.isNullOrEmpty()) {
            Resource.Empty
        } else {
            Resource.Success(this)
        }
        is BaseModel? -> if (this == null || this.isEmpty()) {
            Resource.Empty
        } else {
            Resource.Success(this)
        }
        else -> if (this == null) {
            Resource.Empty
        } else {
            Resource.Success(this)
        }
    }
}

inline fun <T : Any> resource(codeBlock: () -> Result<T>): Resource<T> {
    return try {
        when (val result = codeBlock()) {
            is Result.Success -> result.value.successOrEmpty()
            is Result.Error -> {
                Logger.e(result.throwable.toString())
                Resource.Error(message = result.throwable.toString(), error = result.throwable)
            }
            is Result.Loading -> Resource.Loading
        }
    } catch (e: Exception) {
        Logger.e(e.toString())
        Resource.Error(e.message ?: "No error message")
    }
}

inline fun <T : Any, R : Any> Resource<T>.map(transform: (Resource<T>) -> Resource<R>): Resource<R> {
    return transform(this)
}

fun <T : Any> Resource<T>.data(): T? {
    return if (this is Resource.Success) {
        this.data
    } else {
        null
    }
}