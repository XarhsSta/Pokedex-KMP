package data.repository

import data.models.PokemonInfo
import data.network.PokeApiService

interface PokemonRepository {
    suspend fun getPokemonById(id: Int): Result<PokemonInfo>
}

class DefaultPokemonRepository(
    private val pokeApiService: PokeApiService
): PokemonRepository {

    override suspend fun getPokemonById(id: Int): Result<PokemonInfo> {
        return pokeApiService.getPokemonById(id)
    }
}