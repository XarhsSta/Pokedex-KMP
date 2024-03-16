package data.models.entity

data class DetailedPokemonMove(
    val name: String,
    val pp: Int,
    val power: Int,
    val accuracy: Int
): BaseModel {
    companion object {
        fun empty() = DetailedPokemonMove(
            name = "",
            pp = 0,
            power = 0,
            accuracy = 0
        )
    }

    override fun isEmpty(): Boolean = this == empty()

    override fun isNotEmpty(): Boolean = this != empty()
}
