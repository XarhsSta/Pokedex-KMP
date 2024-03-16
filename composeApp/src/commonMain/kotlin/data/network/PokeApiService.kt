package data.network

import data.ErrorResponse
import data.Failure
import data.Result
import data.models.PagedResponse
import data.models.response.DetailedPokemonInfoResponse
import data.models.response.DetailedPokemonMoveResponse
import data.models.response.PagedPokemonInfoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

class PokeApiService(val httpClient: HttpClient) {

    suspend fun getPokemon(page: Int): Result<PagedResponse<PagedPokemonInfoResponse>> {
        return httpClient.get("pokemon/?limit=20&offset=${(page - 1) * 20}").toResult()
    }

    suspend fun getPokemonById(id: Int): Result<DetailedPokemonInfoResponse> {
        return httpClient.get("pokemon/$id").toResult()
    }

    suspend fun getPokemonByName(name: String): Result<DetailedPokemonInfoResponse> {
        return httpClient.get("pokemon/$name").toResult()
    }

    suspend fun getMoveById(id: Int): Result<DetailedPokemonMoveResponse> {
        return httpClient.get("move/$id").toResult()
    }

}

private suspend inline fun <reified T> HttpResponse.toResult(): Result<T> {
    return try {
        if (status.isSuccess()) {
            val res: T = call.body()
            Result.Success(res)
        } else {
            val res = call.body<ErrorResponse>()
            Result.Error(Failure.UnknownError)
        }
    } catch (ex: Throwable) {
        Result.Error(ex)
    }
}