package ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import co.touchlab.kermit.Logger
import data.fold
import data.models.entity.PokemonInfo
import data.network.PokeApiService
import data.repository.DefaultPokemonRepository
import domain.GetPokemonByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ViewModel {
    val pokemon = mutableStateOf(PokemonInfo.empty())

    val pokeApiService = PokeApiService()
    val pokemonRepository = DefaultPokemonRepository(pokeApiService)
    val getPokemonByIdUseCase = GetPokemonByIdUseCase(pokemonRepository)

    fun getPokemonById(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            getPokemonByIdUseCase(GetPokemonByIdUseCase.Params(id))
//            pokeApiService.getPokemon(1, 0)
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
}