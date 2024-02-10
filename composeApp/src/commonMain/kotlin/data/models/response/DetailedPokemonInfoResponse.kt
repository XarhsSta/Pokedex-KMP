package data.models.response

import data.models.entity.PokemonStat
import data.models.entity.PokemonInfo
import data.models.response.PokemonSpriteResponse.Companion.toPokemonSprite
import data.models.response.PokemonTypesResponse.Companion.toPokemonType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import util.getStat

@Serializable
data class DetailedPokemonInfoResponse(
    val id: Int,
    val name: String,
    @SerialName("sprites")
    val sprite: PokemonSpriteResponse,
    @SerialName("types")
    val type: List<PokemonTypesResponse>,
    @SerialName("stats")
    val stats: List<PokemonStatResponse>
) {
    companion object {
        fun DetailedPokemonInfoResponse.toPokemonInfo() = PokemonInfo(
            id = id,
            name = name,
            sprite = sprite.toPokemonSprite(),
            type = type.map { it.toPokemonType() },
            stats = stats.mapIndexed { index, it ->  PokemonStat(index.getStat(), it.baseStat, it.effort) }
        )
    }
}
