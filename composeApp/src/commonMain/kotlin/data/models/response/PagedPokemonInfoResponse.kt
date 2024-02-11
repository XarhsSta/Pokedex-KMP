package data.models.response

import data.models.entity.PagedPokemonInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagedPokemonInfoResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
) {
    companion object {
        fun PagedPokemonInfoResponse.toPagedPokemonInfo() = PagedPokemonInfo(
            name = name,
            url = url
        )
    }
}
