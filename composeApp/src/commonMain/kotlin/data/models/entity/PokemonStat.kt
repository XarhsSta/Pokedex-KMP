package data.models.entity

data class PokemonStat(
    val name: Stat,
    val baseStat: Int,
    val effort: Int
): BaseModel {
    companion object {
        fun empty() = PokemonStat(
            name = Stat.UNKNOWN,
            baseStat = 0,
            effort = 0
        )
    }

    override fun isEmpty(): Boolean = this == empty()

    override fun isNotEmpty(): Boolean = this != empty()
}
