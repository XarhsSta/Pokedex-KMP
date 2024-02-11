package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import data.models.entity.PokemonInfo
import data.models.entity.PokemonStat
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import ui.ViewModel
import util.capitalize
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun DetailedViewScreen() {
    val viewModel = ViewModel()
    val pokemon by remember { viewModel.pokemon }
    viewModel.getPokemonById(6)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(pokemon.name.capitalize(), modifier = Modifier.padding(vertical = 8.dp))
        PokemonImage(pokemon)
        PokemonStats(pokemon.stats)
    }
}

@Composable
private fun PokemonImage(pokemon: PokemonInfo) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .gradientBackground(
                colors = pokemonGradientColors(pokemon),
                angle = 45f
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (pokemon.isNotEmpty()) {
            KamelImage(
                resource = asyncPainterResource(data = pokemon.sprite.defaultSprite),
                contentDescription = "Pokemon Default Sprite",
                modifier = Modifier.size(256.dp)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
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

@Composable
private fun PokemonStats(stats: List<PokemonStat>) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Base Stats",
            modifier = Modifier.padding(vertical = 8.dp)
        )
        stats.forEach { StatEntry(it) }
        Text(
            text = "Total Base Stat: ${stats.map { it.baseStat }.sum()}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    }
}

@Composable
private fun StatEntry(stat: PokemonStat) {
    Row(
        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stat.name.formattedName, modifier = Modifier.weight(0.2f))
        StatBar(stat.baseStat, stat.name.color)
        Text(
            text = stat.baseStat.toString(),
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun RowScope.StatBar(baseValue: Int, color: Color) {
    val maxBaseStat = 255
    val widthToFill = baseValue.toDouble() / maxBaseStat
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(1f)
    ) {
        Spacer(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .fillMaxWidth(widthToFill.toFloat())
                .fillMaxHeight()
                .background(color)
        )
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

private fun String.toColor(): Color {
    val rgb = this.chunked(2)
    return Color(red = rgb[0].toInt(16), green = rgb[1].toInt(16), blue = rgb[2].toInt(16))
}

@Composable
fun Modifier.gradientBackground(colors: List<Color>, angle: Float) = this.then(
    Modifier.drawBehind {
        val angleRad = angle / 180f * PI
        val x = cos(angleRad).toFloat() //Fractional x
        val y = sin(angleRad).toFloat() //Fractional y

        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)

        val exactOffset = Offset(
            x = min(offset.x.coerceAtLeast(0f), size.width),
            y = size.height - min(offset.y.coerceAtLeast(0f), size.height)
        )

        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )
    }
)