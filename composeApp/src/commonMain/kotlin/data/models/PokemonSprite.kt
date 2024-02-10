package data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSprite(
    @SerialName("front_default")
    val defaultSprite: String,
    @SerialName("front_shiny")
    val frontShiny: String
)
