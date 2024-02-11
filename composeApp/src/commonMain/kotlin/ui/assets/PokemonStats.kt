package ui.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import data.models.entity.PokemonStat
import util.StatBar
import util.TableCell


@Composable
fun PokemonStats(stats: List<PokemonStat>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(
                RoundedCornerShape(10.dp)
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