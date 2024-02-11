package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.getKoin
import ui.assets.PokemonAbilities
import ui.assets.PokemonBiology
import ui.assets.PokemonImage
import ui.assets.PokemonStats
import ui.viewmodel.ViewModel
import util.capitalize

class DetailedViewScreen(val index: Int): Screen {
    @Composable
    override fun Content() {
        val viewModel: ViewModel = getKoin().get<ViewModel>().also {
            it.getPokemonById(index)
        }
        val pokemon by remember { viewModel.pokemon }
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
