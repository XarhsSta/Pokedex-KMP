package data.models.entity

data class PokemonInfo(
    val id: Int,
    val name: String,
    val sprite: PokemonSprite,
    val type: List<PokemonType>,
    val stats: List<PokemonStat>,
    val abilities: List<PokemonAbility>,
    val height: Int,
    val weight: Int,
    val moves: List<PokemonMove>
): BaseModel {
    companion object {
        fun empty() = PokemonInfo(
            id = 0,
            name = "",
            sprite = PokemonSprite.empty(),
            type = emptyList(),
            stats = emptyList(),
            abilities = emptyList(),
            height = 0,
            weight = 0,
            moves = emptyList()
        )
    }

    override fun isEmpty() = this == empty()

    override fun isNotEmpty() = this != empty()
}