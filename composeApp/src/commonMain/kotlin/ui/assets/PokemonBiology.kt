package ui.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import util.biologyFormat
import util.toAlphaColor
import util.toColor

@Composable
fun PokemonBiology(height: Int, weight: Int, modifier: Modifier, backgroundColor: String) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .background(backgroundColor.toAlphaColor()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Biology Stats",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor.toColor()),
                textAlign = TextAlign.Center
            )
            Text(text = "Height: ${height.biologyFormat()} m")
            Text(text = "Weight: ${weight.biologyFormat()} kg")
        }
    }
}