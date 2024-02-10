package data.network

import data.models.PokemonInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
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

    suspend fun getPokemonById(id: Int): PokemonInfo {
        val response = httpClient.get("pokemon/$id")
        return response.body()
    }

}