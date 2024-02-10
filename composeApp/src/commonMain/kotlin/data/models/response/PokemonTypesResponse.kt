package data.models.response

import data.models.entity.PokemonType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypesResponse(
    @SerialName("slot")
    val slot: Int,
    @SerialName("type")
    val type: PokemonTypeResponse
) {
    companion object {
        fun PokemonTypesResponse.toPokemonType() = PokemonType.valueOf(this.type.name.uppercase())
    }
}
