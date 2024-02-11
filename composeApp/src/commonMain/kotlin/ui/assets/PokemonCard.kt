package ui.assets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.models.entity.PagedPokemonInfo
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import ui.screens.DetailedViewScreen
import util.capitalize


@Composable
fun PokemonCard(pokemon: PagedPokemonInfo) {
    val navigator = LocalNavigator.currentOrThrow
    Card {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    navigator.push(DetailedViewScreen(pokemon.getIndex()))
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KamelImage(
                resource = asyncPainterResource(pokemon.getImageUrl()),
                contentDescription = "Pokemon Sprite",
                modifier = Modifier.size(96.dp)
            )
            Text(pokemon.name.capitalize())
        }
    }
}