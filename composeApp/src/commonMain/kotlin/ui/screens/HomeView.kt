package ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.getKoin
import ui.assets.PokemonCard
import ui.viewmodel.ViewModel
import util.PagingGrid

object HomeView : Screen {
    @Composable
    override fun Content() {
        val viewModel: ViewModel = getKoin().get()
        val result by rememberUpdatedState(viewModel.pokemonFlow.collectAsLazyPagingItems())

        PagingGrid(data = result) {
            PokemonCard(it)
        }
    }
}