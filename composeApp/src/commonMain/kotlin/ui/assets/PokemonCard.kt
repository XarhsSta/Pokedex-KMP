package ui.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.models.entity.PagedPokemonInfo
import ui.screens.DetailedViewScreen
import util.capitalize
import util.getIndex
import util.toColor


@Composable
fun PokemonCard(pokemon: PagedPokemonInfo) {
    val navigator = LocalNavigator.currentOrThrow
    Card(
        modifier = Modifier.clickable {
                navigator.push(DetailedViewScreen(getIndex(pokemon.url)))
            },
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PokemonImage(
                spriteUrl = pokemon.getImageUrl(),
                imageSize = 128.dp,
                verticalPadding = 8.dp
            )
            Text(
                text = pokemon.name.capitalize(),
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 18.sp
            )
        }
    }
}