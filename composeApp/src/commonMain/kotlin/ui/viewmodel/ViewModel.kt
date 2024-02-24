package ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import data.models.entity.PagedPokemonInfo
import data.models.entity.PokemonInfo
import data.repository.PokemonRepository
import domain.GetPokemonByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import util.Resource
import util.resource

class ViewModel(
    val getPokemonByIdUseCase: GetPokemonByIdUseCase,
    val pokemonRepository: PokemonRepository
) {
    val pokemon: MutableState<Resource<PokemonInfo>> = mutableStateOf(Resource.Empty)
    val pokemonFlow = getPokemon()

    fun getPokemonById(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            pokemon.value = resource { getPokemonByIdUseCase(GetPokemonByIdUseCase.Params(id)) }
        }
    }

    fun getPokemon(): Flow<PagingData<PagedPokemonInfo>> {
        return pokemonRepository.getPokemon()
    }
}