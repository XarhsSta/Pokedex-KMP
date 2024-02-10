package data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpriteResponse(
    @SerialName("front_default")
    val defaultSprite: String,
    @SerialName("front_shiny")
    val frontShiny: String
)
