package data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import util.getStat

@Serializable
data class PokemonInfoResponse(
    val id: Int,
    val name: String,
    @SerialName("sprites")
    val sprite: PokemonSprite,
    @SerialName("types")
    val type: List<PokemonTypes>,
    @SerialName("stats")
    val stats: List<PokemonStatResponse>
) {
    companion object {
        fun PokemonInfoResponse.toPokemonInfo() = PokemonInfo(
            id = id,
            name = name,
            sprite = sprite,
            type = type,
            stats = stats.mapIndexed { index, it ->  PokemonStat(index.getStat(), it.baseStat, it.effort) }
        )
    }
}

data class PokemonInfo(
    val id: Int,
    val name: String,
    val sprite: PokemonSprite,
    val type: List<PokemonTypes>,
    val stats: List<PokemonStat>
)