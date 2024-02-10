package data

sealed class Failure: Throwable() {
    object UnknownError: Failure()
}