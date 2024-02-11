package data.models.entity

data class PagedPokemonInfo(
    val name: String,
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
