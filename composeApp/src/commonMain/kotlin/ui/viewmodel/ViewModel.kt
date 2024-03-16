package ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import co.touchlab.kermit.Logger
import data.models.entity.PagedPokemonInfo
import data.models.entity.PokemonInfo
import data.repository.MovesRepository
import data.repository.PokemonRepository
import data.Result
import domain.GetPokemonByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import util.RequestState
import util.RequestState.Idle.requestState
import util.Resource
import util.resource

class ViewModel(
    val getPokemonByIdUseCase: GetPokemonByIdUseCase,
    val pokemonRepository: PokemonRepository,
    val movesRepository: MovesRepository
) {
    val pokemonFlow = getPokemon()

    fun getPokemonById(id: Int): Flow<RequestState<PokemonInfo>> {
        return flow {
            emit(requestState { getPokemonByIdUseCase(GetPokemonByIdUseCase.Params(id)) })
        }
    }

    fun getPokemon(): Flow<PagingData<PagedPokemonInfo>> {
        return pokemonRepository.getPokemon()
    }
}