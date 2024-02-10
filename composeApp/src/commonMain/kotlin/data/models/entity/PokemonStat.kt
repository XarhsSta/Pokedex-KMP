package data.models.entity

data class PokemonStat(
    val name: Stat,
    val baseStat: Int,
    val effort: Int
) {
    companion object {
        fun empty() = PokemonStat(
            name = Stat.UNKNOWN,
            baseStat = 0,
            effort = 0
        )
    }
}
