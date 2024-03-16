package ui.viewmodel

import androidx.paging.PagingData
import data.models.entity.PagedPokemonInfo
import data.models.entity.PokemonInfo
import data.repository.PokemonRepository
import domain.GetPokemonByIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import util.RequestState
import util.RequestState.Idle.requestState

class ViewModel(
    val getPokemonByIdUseCase: GetPokemonByIdUseCase,
    val pokemonRepository: PokemonRepository
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