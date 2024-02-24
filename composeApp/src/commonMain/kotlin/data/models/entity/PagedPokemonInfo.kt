package data.models.entity

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
    fun getIndex(): Int {
        return url.split("/".toRegex()).dropLast(1).last().toInt()
    }
    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                "pokemon/other/official-artwork/${getIndex()}.png"
    }

    override fun isEmpty(): Boolean = this == empty()

    override fun isNotEmpty(): Boolean = this != empty()
}
