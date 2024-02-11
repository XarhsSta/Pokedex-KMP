package data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagedPokemonInfoResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
) {
    fun getIndex(): Int {
        return url.split("/".toRegex()).dropLast(1).last().toInt()
    }
    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                "pokemon/other/official-artwork/${getIndex()}.png"
    }
}
