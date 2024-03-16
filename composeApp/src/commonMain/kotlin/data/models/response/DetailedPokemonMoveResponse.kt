package data.models.response

import data.models.entity.DetailedPokemonMove
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailedPokemonMoveResponse(
    @SerialName("name")
    val name: String,
    @SerialName("pp")
    val pp: Int,
    @SerialName("power")
    val power: Int,
    @SerialName("accuracy")
    val accuracy: Int
) {
    companion object {
        fun DetailedPokemonMoveResponse.toDetailedPokemonMove() = DetailedPokemonMove(
            name = name,
            pp = pp,
            power = power,
            accuracy = accuracy
        )
    }
}
