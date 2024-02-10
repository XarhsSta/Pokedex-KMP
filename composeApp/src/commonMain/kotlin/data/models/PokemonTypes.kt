package data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypes(
    @SerialName("slot")
    val slot: Int,
    @SerialName("type")
    val type: PokemonType
)
