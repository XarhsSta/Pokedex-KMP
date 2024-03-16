package data.models.entity

import util.getIndex

data class PagedPokemonInfo(
    val name: String,
    val url: String
): BaseModel {
    companion object {
        fun empty() = PagedPokemonInfo(
            name = "",
            url = ""
        )
    }
    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                "pokemon/other/official-artwork/${getIndex(url)}.png"
    }

    override fun isEmpty(): Boolean = this == empty()

    override fun isNotEmpty(): Boolean = this != empty()
}
