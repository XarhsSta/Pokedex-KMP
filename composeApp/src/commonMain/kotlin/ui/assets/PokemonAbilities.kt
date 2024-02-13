package ui.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import data.models.entity.PokemonAbility
import util.capitalizeWords
import util.dedash
import util.toAlphaColor
import util.toColor

@Composable
fun PokemonAbilities(
    abilities: List<PokemonAbility>,
    modifier: Modifier,
    backgroundColor: String
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier
                    .background(backgroundColor.toColor())
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Abilities",
                    fontWeight = FontWeight.Bold
                )
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
            if (abilities.filter { it.isHidden }.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .background(backgroundColor.toColor())
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Hidden Abilities",
                        fontWeight = FontWeight.Bold
                    )
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
}
