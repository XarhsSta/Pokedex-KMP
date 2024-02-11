package data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import co.touchlab.kermit.Logger
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

    fun getPokemon(): Flow<PagingData<PagedPokemonInfoResponse>>
}

class DefaultPokemonRepository(
    private val pokeApiService: PokeApiService
): PokemonRepository {

    override suspend fun getPokemonById(id: Int): Result<PokemonInfo> {
        return pokeApiService.getPokemonById(id).map { it.toPokemonInfo() }
    }

    override fun getPokemon(): Flow<PagingData<PagedPokemonInfoResponse>> = Pager(
        config = PagingConfig(pageSize = 20, initialLoadSize = 20),
        pagingSourceFactory = {
            ResultPagingSource { page, _ ->
                pokeApiService.getPokemon(page).map { it.results }
            }
        }
    ).flow
}