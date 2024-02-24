package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import co.touchlab.kermit.Logger
import data.models.entity.PokemonInfo
import org.koin.compose.getKoin
import ui.assets.PokedexTopAppBar
import ui.assets.PokemonAbilities
import ui.assets.PokemonBiology
import ui.assets.PokemonImage
import ui.assets.PokemonStats
import ui.viewmodel.ViewModel
import util.Resource
import util.capitalize

class DetailedViewScreen(val index: Int): Screen {
    @Composable
    override fun Content() {
        val viewModel: ViewModel = getKoin().get<ViewModel>().also {
            it.getPokemonById(index)
        }
        val pokemon by remember { viewModel.pokemon }
        when (pokemon) {
            is Resource.Success -> {
                with((pokemon as Resource.Success<PokemonInfo>).data) {
                    Scaffold(
                        topBar = {
                            PokedexTopAppBar(
                                this@DetailedViewScreen,
                                name.capitalize()
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            PokemonImage(sprite.defaultSprite, type)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (type.isNotEmpty()) {
                                    PokemonAbilities(
                                        abilities,
                                        modifier = Modifier.weight(1f),
                                        type[0].hexColor
                                    )
                                    PokemonBiology(
                                        height = height,
                                        weight = weight,
                                        modifier = Modifier.weight(1f),
                                        type[0].hexColor
                                    )
                                }
                            }
                            if (type.isNotEmpty()) {
                                PokemonStats(stats, type[0].hexColor)
                            }
                        }
                    }
                }
            }
            is Resource.Error -> {
                Logger.e((this as Resource.Error).message)
            }
            is Resource.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is Resource.Empty -> {
                Logger.d("Empty data")
            }
        }
    }
}
