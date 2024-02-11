package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import data.models.entity.PokemonInfo
import data.models.entity.PokemonStat
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import ui.ViewModel
import util.StatBar
import util.TableCell
import util.capitalize
import util.gradientBackground
import util.toColor

@Composable
fun DetailedViewScreen() {
    val viewModel = ViewModel()
    val pokemon by remember { viewModel.pokemon }
    viewModel.getPokemonById(6)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = pokemon.name.capitalize(),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        PokemonImage(pokemon)
        PokemonStats(pokemon.stats)
    }
}

@Composable
private fun PokemonImage(pokemon: PokemonInfo) {
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

@Composable
private fun PokemonStats(stats: List<PokemonStat>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.background(Color.Gray)) {
            TableCell(text = "Base Stats")
        }
        stats.forEach { StatEntry(it) }
        Row(modifier = Modifier.background(Color.Gray)) {
            TableCell(text = "Total Base Stat: ${stats.map { it.baseStat }.sum()}")
        }
    }
}

@Composable
private fun StatEntry(stat: PokemonStat) {
    Row(
        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TableCell(
            text = stat.name.formattedName,
            weight = 0.2f
        )
        StatBar(
            baseStat = stat.baseStat,
            color = stat.name.color
        )
        TableCell(
            text = stat.baseStat.toString(),
            weight = 0.2f,
            textAlign = TextAlign.End
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