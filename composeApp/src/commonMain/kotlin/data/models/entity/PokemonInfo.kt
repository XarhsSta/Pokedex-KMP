package data.models.entity

import data.models.response.PokemonSpriteResponse
import data.models.response.PokemonTypesResponse

data class PokemonInfo(
    val id: Int,
    val name: String,
    val sprite: PokemonSprite,
    val type: List<PokemonType>,
    val stats: List<PokemonStat>
) {
    companion object {
        fun empty() = PokemonInfo(
            id = 0,
            name = "",
            sprite = PokemonSprite.empty(),
            type = emptyList(),
            stats = emptyList()
        )
    }
}