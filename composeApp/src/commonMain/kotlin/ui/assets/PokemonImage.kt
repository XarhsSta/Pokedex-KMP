package ui.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.models.entity.PokemonInfo
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import util.gradientBackground
import util.toColor

@Composable
fun PokemonImage(pokemon: PokemonInfo) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .gradientBackground(
                colors = pokemonGradientColors(pokemon),
                angle = 45f
            )
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (pokemon.isNotEmpty()) {
            KamelImage(
                resource = asyncPainterResource(data = pokemon.sprite.defaultSprite),
                contentDescription = "Pokemon Default Sprite",
                modifier = Modifier
                    .size(256.dp)
                    .padding(top = 16.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            pokemon.type.forEach {
                KamelImage(
                    resource = asyncPainterResource(data = it.imageUrl),
                    contentDescription = "Pokemon Type",
                    modifier = Modifier.padding(horizontal = 8.dp).size(96.dp)
                )
            }
        }
    }
}

private fun pokemonGradientColors(pokemon: PokemonInfo): List<Color> {
    return if (pokemon.type.size == 1) {
        listOf(
            pokemon.type[0].hexColor.toColor(),
            Color.White
        )
    } else {
        pokemon.type.map { it.hexColor.toColor() }
    }
}