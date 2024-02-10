package data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypesResponse(
    @SerialName("slot")
    val slot: Int,
    @SerialName("type")
    val type: PokemonTypeResponse
)
