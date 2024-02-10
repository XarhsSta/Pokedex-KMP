package data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonInfo(
    val id: Int,
    val name: String,
    @SerialName("sprites")
    val sprite: PokemonSprite,
    @SerialName("types")
    val type: List<PokemonTypes>
)
