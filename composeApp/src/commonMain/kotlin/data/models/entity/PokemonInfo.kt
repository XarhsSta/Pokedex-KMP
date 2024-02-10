package data.models.entity

import data.models.response.PokemonSpriteResponse
import data.models.response.PokemonTypesResponse

data class PokemonInfo(
    val id: Int,
    val name: String,
    val sprite: PokemonSpriteResponse,
    val type: List<PokemonTypesResponse>,
    val stats: List<PokemonStat>
)