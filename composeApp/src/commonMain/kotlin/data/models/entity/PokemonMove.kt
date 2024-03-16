package data.models.entity

data class PokemonMove(
    val name: String,
    val url: String
): BaseModel {
    companion object {
        fun empty() = PokemonMove(
            name = "",
            url = ""
        )
    }

    override fun isEmpty(): Boolean = this == empty()

    override fun isNotEmpty(): Boolean = this != empty()
}
