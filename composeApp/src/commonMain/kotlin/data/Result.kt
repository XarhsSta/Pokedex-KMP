@file:OptIn(ExperimentalContracts::class)

package data

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed interface Result<out T> {
    class Success<out T>(val value: T) : Result<T>
    data object Loading : Result<Nothing>
    class Error(val throwable: Throwable) : Result<Nothing>
}

inline fun <T, R> Result<T>.map(transform: (value: T) -> R): Result<R> =
    when (this) {
        is Result.Success -> Result.Success(transform(value))
        is Result.Error -> Result.Error(throwable)
        is Result.Loading -> Result.Loading
    }

inline fun <R, T> Result<T>.fold(
    onSuccess: (value: T) -> R,
    onError: (exception: Throwable) -> R,
    onLoading: () -> R
): R {
    contract {
        callsInPlace(onSuccess, InvocationKind.AT_MOST_ONCE)
        callsInPlace(onError, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is Result.Success -> onSuccess(value)
        is Result.Error -> onError(throwable)
        is Result.Loading -> onLoading()
    }
}