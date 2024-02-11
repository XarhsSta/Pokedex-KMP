package data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import data.ResultPagingSource
import data.Result
import data.map
import data.models.entity.PokemonInfo
import data.models.response.DetailedPokemonInfoResponse.Companion.toPokemonInfo
import data.models.response.PagedPokemonInfoResponse
import data.network.PokeApiService
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonById(id: Int): Result<PokemonInfo>

    fun getPokemon(limit: Int, offset: Int): Flow<PagingData<PagedPokemonInfoResponse>>
}

class DefaultPokemonRepository(
    private val pokeApiService: PokeApiService
): PokemonRepository {

    override suspend fun getPokemonById(id: Int): Result<PokemonInfo> {
        return pokeApiService.getPokemonById(id).map { it.toPokemonInfo() }
    }

    override fun getPokemon(limit: Int, offset: Int): Flow<PagingData<PagedPokemonInfoResponse>> = Pager(
        config = PagingConfig(pageSize = limit, initialLoadSize = limit),
        pagingSourceFactory = {
            ResultPagingSource { page, _ ->
                pokeApiService.getPokemon(page).map { it.results }
            }
        }
    ).flow
}