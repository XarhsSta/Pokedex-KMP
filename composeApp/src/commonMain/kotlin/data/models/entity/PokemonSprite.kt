package data.models.entity

data class PokemonSprite(
    val defaultSprite: String,
    val frontShiny: String
): BaseModel {
    companion object {
        fun empty() = PokemonSprite(
            defaultSprite = "",
            frontShiny = ""
        )
    }

    override fun isEmpty(): Boolean = this == empty()

    override fun isNotEmpty(): Boolean = this != empty()
}
