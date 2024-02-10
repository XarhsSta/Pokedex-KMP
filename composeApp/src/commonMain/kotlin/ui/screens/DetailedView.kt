package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.models.entity.PokemonInfo
import data.models.entity.PokemonStat
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import ui.ViewModel
import util.capitalize

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
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (pokemon.isNotEmpty()) {
            KamelImage(
                resource = asyncPainterResource(data = pokemon.sprite.defaultSprite),
                contentDescription = "Pokemon Default Sprite",
                modifier = Modifier.size(256.dp)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            pokemon.type.forEach {
                Text(text = it.name, modifier = Modifier.padding(horizontal = 8.dp))
            }
        }
    }
}

@Composable
private fun PokemonStats(stats: List<PokemonStat>) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Base Stats", modifier = Modifier.padding(vertical = 8.dp))
        stats.forEach { StatEntry(it) }
    }
}

@Composable
private fun StatEntry(stat: PokemonStat) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stat.name.formattedName)
        Text(text = stat.baseStat.toString())
    }
}