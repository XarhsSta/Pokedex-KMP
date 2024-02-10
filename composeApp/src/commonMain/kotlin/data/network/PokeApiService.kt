package data.network

import data.ErrorResponse
import data.Failure
import data.models.PagedResponse
import data.models.response.DetailedPokemonInfoResponse
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

    suspend fun getPokemon(limit: Int, offset: Int): Result<PagedResponse<DetailedPokemonInfoResponse>> {
        return httpClient.get("pokemon/?limit=$limit&offset=$offset").toResult()
    }

    suspend fun getPokemonById(id: Int): Result<DetailedPokemonInfoResponse> {
        return httpClient.get("pokemon/$id").toResult()
    }

    suspend fun getPokemonByName(name: String): Result<DetailedPokemonInfoResponse> {
        return httpClient.get("pokemon/$name").toResult()
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