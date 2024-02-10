package data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonStatResponse(
    @SerialName("base_stat")
    val baseStat: Int,
    @SerialName("effort")
    val effort: Int
)

data class PokemonStat(
    val name: Stat,
    val baseStat: Int,
    val effort: Int
)
