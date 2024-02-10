package data.models.response

import data.models.entity.PokemonSprite
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpriteResponse(
    @SerialName("front_default")
    val defaultSprite: String,
    @SerialName("front_shiny")
    val frontShiny: String
) {
    companion object {
        fun PokemonSpriteResponse.toPokemonSprite() = PokemonSprite(
            defaultSprite = defaultSprite,
            frontShiny = frontShiny
        )
    }
}
