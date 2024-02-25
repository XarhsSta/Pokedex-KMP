package data.models.response

import data.models.entity.PokemonMove
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PokemonMovesResponse(
    @SerialName("move")
    val move: PokemonMoveResponse,
) {
    companion object {
        fun PokemonMovesResponse.toPokemonMoves() = PokemonMove(
            name = move.name,
            url = move.url
        )
    }
}

@Serializable
data class PokemonMoveResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
