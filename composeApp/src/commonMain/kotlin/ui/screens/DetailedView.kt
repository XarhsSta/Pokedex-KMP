package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import co.touchlab.kermit.Logger
import org.koin.compose.getKoin
import ui.assets.PokedexTopAppBar
import ui.assets.PokemonAbilities
import ui.assets.PokemonBiology
import ui.assets.PokemonImage
import ui.assets.PokemonStats
import ui.viewmodel.ViewModel
import util.RequestState
import util.capitalize

class DetailedViewScreen(val index: Int) : Screen {
    @Composable
    override fun Content() {
        val viewModel: ViewModel = getKoin().get<ViewModel>()
        val pokemon by viewModel.getPokemonById(index).collectAsState(initial = RequestState.Idle)
        pokemon.DisplayResult(
            onLoading = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            onSuccess = {
                pokemon.getSuccessDataOrNull()?.let { pokemon ->
                    Scaffold(
                        topBar = {
                            PokedexTopAppBar(
                                this@DetailedViewScreen,
                                pokemon.name.capitalize()
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            PokemonImage(pokemon.sprite.defaultSprite, pokemon.type)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
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
                            PokemonStats(pokemon.stats, pokemon.type[0].hexColor)
                        }
                    }
                }
            },
            onError = {
                Logger.e(pokemon.getErrorMessageOrNull() ?: "Can't find error message")
            }
        )
    }
}
