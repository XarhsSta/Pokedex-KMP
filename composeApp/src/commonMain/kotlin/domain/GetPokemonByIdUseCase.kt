package domain

import data.Result
import data.models.entity.PokemonInfo
import data.repository.PokemonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class GetPokemonByIdUseCase(
    private val pokemonRepository: PokemonRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): UseCase<GetPokemonByIdUseCase.Params, PokemonInfo>(dispatcher) {

    data class Params(val id: Int)

    override suspend fun execute(parameters: Params): Result<PokemonInfo> {
        return pokemonRepository.getPokemonById(parameters.id)
    }
}