package ui.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import util.biologyFormat
import util.toAlphaColor

@Composable
fun PokemonBiology(height: Int, weight: Int, modifier: Modifier, backgroundColor: String) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor.toAlphaColor()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Height: ${height.biologyFormat()} m")
        Text(text = "Weight: ${weight.biologyFormat()} kg")
    }
}