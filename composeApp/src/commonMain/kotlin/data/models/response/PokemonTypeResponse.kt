package data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
