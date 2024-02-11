package data.models.response

import data.models.entity.PokemonAbility
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonAbilitiesResponse(
    @SerialName("ability")
    val ability: PokemonAbilityResponse,
    @SerialName("is_hidden")
    val isHidden: Boolean,
    @SerialName("slot")
    val slot: Int
) {
    companion object {
        fun PokemonAbilitiesResponse.toPokemonAbility() = PokemonAbility(
            name = this.ability.name,
            isHidden = isHidden
        )
    }
}
