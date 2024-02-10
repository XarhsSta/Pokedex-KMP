package data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonType(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
