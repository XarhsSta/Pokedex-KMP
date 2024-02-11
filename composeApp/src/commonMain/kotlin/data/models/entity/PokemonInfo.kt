package data.models.entity

import data.models.response.PokemonSpriteResponse
import data.models.response.PokemonTypesResponse

data class PokemonInfo(
    val id: Int,
    val name: String,
    val sprite: PokemonSprite,
    val type: List<PokemonType>,
    val stats: List<PokemonStat>,
    val abilities: List<PokemonAbility>,
    val height: Int,
    val weight: Int
) {
    companion object {
        fun empty() = PokemonInfo(
            id = 0,
            name = "",
            sprite = PokemonSprite.empty(),
            type = emptyList(),
            stats = emptyList(),
            abilities = emptyList(),
            height = 0,
            weight = 0
        )
    }

    fun isEmpty() = this == empty()

    fun isNotEmpty() = !isEmpty()
}