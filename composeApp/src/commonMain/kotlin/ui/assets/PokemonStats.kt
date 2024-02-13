package ui.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import data.models.entity.PokemonStat
import util.StatBar
import util.toAlphaColor
import util.toColor


@Composable
fun PokemonStats(stats: List<PokemonStat>, backgroundColor: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(
                RoundedCornerShape(10.dp)
            )
            .background(backgroundColor.toAlphaColor())
    ) {
        Row {
            Text(
                text = "Base Stats",
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold
            )
        }
        stats.forEach { StatEntry(it) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Total Base Stat: ",
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .padding(start = 8.dp)
            )
            Text(
                text = stats.map { it.baseStat }.sum().toString(),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun StatEntry(stat: PokemonStat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(stat.name.backgroundHexColor.toColor()
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .border(1.dp, Color.Black)
                .weight(0.4f)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stat.name.formattedName,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stat.baseStat.toString(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
        }
        StatBar(
            baseStat = stat.baseStat,
            color = stat.name.hexColor.toColor()
        )
    }
}