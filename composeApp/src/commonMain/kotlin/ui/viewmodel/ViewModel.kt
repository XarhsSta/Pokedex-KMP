package ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import co.touchlab.kermit.Logger
import data.fold
import data.models.entity.PokemonInfo
import data.models.response.PagedPokemonInfoResponse
import data.network.PokeApiService
import data.repository.DefaultPokemonRepository
import domain.GetPokemonByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ViewModel {
    val pokemon = mutableStateOf(PokemonInfo.empty())

    val pokeApiService = PokeApiService()
    val pokemonRepository = DefaultPokemonRepository(pokeApiService)
    val getPokemonByIdUseCase = GetPokemonByIdUseCase(pokemonRepository)

    val pokemonFlow = getPokemon()

    fun getPokemonById(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            getPokemonByIdUseCase(GetPokemonByIdUseCase.Params(id))
                .fold(
                    onSuccess = {
                        Logger.d(it.toString())
                        pokemon.value = it
                    },
                    onError = {
                        Logger.w(it.toString())
                    },
                    onLoading = {
                        Logger.d("It is Loading")
                    }
                )
        }
    }

    fun getPokemon(): Flow<PagingData<PagedPokemonInfoResponse>> {
        return pokemonRepository.getPokemon()
    }
}