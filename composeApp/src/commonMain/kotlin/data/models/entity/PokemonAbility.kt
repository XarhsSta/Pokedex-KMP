package data.models.entity

data class PokemonAbility(
    val name: String,
    val isHidden: Boolean
): BaseModel {
    companion object {
        fun empty() = PokemonAbility(
            name = "",
            isHidden = false
        )
    }

    override fun isEmpty(): Boolean = this == empty()

    override fun isNotEmpty(): Boolean = this != empty()
}
