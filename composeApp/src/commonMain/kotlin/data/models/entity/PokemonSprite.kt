package data.models.entity

data class PokemonSprite(
    val defaultSprite: String,
    val frontShiny: String
) {
    companion object {
        fun empty() = PokemonSprite(
            defaultSprite = "",
            frontShiny = ""
        )
    }
}