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
import cafe.adriel.voyager.core.screen.Screen
import data.models.entity.PokemonAbility
import data.models.entity.PokemonInfo
import data.models.entity.PokemonStat
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.getKoin
import ui.viewmodel.ViewModel
import util.StatBar
import util.TableCell
import util.biologyFormat
import util.capitalize
import util.capitalizeWords
import util.dedash
import util.gradientBackground
import util.toAlphaColor
import util.toColor


class DetailedViewScreen(val index: Int): Screen {
    @Composable
    override fun Content() {
        val viewModel: ViewModel = getKoin().get()
        val pokemon by remember { viewModel.pokemon }
        viewModel.getPokemonById(index)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = pokemon.name.capitalize(),
                modifier = Modifier.padding(vertical = 8.dp)
            )
            PokemonImage(pokemon)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (pokemon.type.isNotEmpty()) {
                    PokemonAbilities(
                        pokemon.abilities,
                        modifier = Modifier.weight(1f),
                        pokemon.type[0].hexColor
                    )
                    PokemonBiology(
                        height = pokemon.height,
                        weight = pokemon.weight,
                        modifier = Modifier.weight(1f),
                        pokemon.type[0].hexColor
                    )
                }
            }
            PokemonStats(pokemon.stats)
        }
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
private fun PokemonAbilities(
    abilities: List<PokemonAbility>,
    modifier: Modifier,
    backgroundColor: String
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .background(backgroundColor.toColor())
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Abilities")
        }
        Column(
            modifier = Modifier
                .background(backgroundColor.toAlphaColor())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            abilities.filter { !it.isHidden }.forEach {
                Text(
                    text = it.name.dedash().capitalizeWords()
                )
            }
        }
        if(abilities.filter { it.isHidden }.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .background(backgroundColor.toColor())
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Hidden Abilities")
            }
            abilities.filter { it.isHidden }.forEach {
                Text(
                    text = it.name.dedash().capitalizeWords(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor.toAlphaColor()),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun PokemonBiology(height: Int, weight: Int, modifier: Modifier, backgroundColor: String) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor.toAlphaColor()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Height: ${height.biologyFormat()} m")
        Text(text = "Weight: ${weight.biologyFormat()} kg")
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