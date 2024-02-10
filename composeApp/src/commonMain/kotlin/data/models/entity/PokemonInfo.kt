package data.models.entity

import data.models.response.PokemonSpriteResponse
import data.models.response.PokemonTypesResponse

data class PokemonInfo(
    val id: Int,
    val name: String,
    val sprite: PokemonSprite,
    val type: List<PokemonType>,
    val stats: List<PokemonStat>
)