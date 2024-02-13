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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import data.models.entity.PokemonInfo
import data.models.entity.PokemonType
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import util.gradientBackground
import util.toColor

@Composable
fun PokemonImage(
    spriteUrl: String,
    types: List<PokemonType> = emptyList(),
    imageSize: Dp = 256.dp,
    verticalPadding: Dp = 16.dp
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .gradientBackground(
                colors = pokemonGradientColors(types),
                angle = 45f
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(spriteUrl.isNotEmpty()) {
            KamelImage(
                resource = asyncPainterResource(data = spriteUrl),
                contentDescription = "Pokemon Default Sprite",
                modifier = Modifier
                    .size(imageSize)
                    .padding(vertical = verticalPadding)
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
            types.forEach {
                KamelImage(
                    resource = asyncPainterResource(data = it.imageUrl),
                    contentDescription = "Pokemon Type",
                    modifier = Modifier.padding(horizontal = 8.dp).size(96.dp)
                )
            }
        }
    }
}

private fun pokemonGradientColors(types: List<PokemonType>): List<Color> {
    return when (types.size)  {
        1 -> listOf(
            types[0].hexColor.toColor(),
            Color.White
        )
        2 -> types.map { it.hexColor.toColor() }
        else -> listOf(
            Color.White,
            Color.Black
        )
    }
}