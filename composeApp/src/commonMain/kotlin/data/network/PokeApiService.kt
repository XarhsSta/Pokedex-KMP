package data.network

import data.ErrorResponse
import data.Failure
import data.models.PokemonInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PokeApiService {
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            url("https://pokeapi.co/api/v2/")
        }
    }

    suspend fun getPokemonById(id: Int): Result<PokemonInfo> {
        val response = httpClient.get("pokemon/$id")
        return response.toResult()
    }

}

private suspend inline fun <reified T> HttpResponse.toResult(): Result<T> {
    return try {
        if (status.isSuccess()) {
            val res: T = call.body()
            Result.success(res)
        } else {
            val res = call.body<ErrorResponse>()
            Result.failure(Failure.UnknownError)
        }
    } catch (ex: Throwable) {
        Result.failure(ex)
    }
}