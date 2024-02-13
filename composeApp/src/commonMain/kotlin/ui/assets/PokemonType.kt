package ui.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.models.entity.PokemonType
import util.TransparentCardWithElevation
import util.toColor

@Composable
fun PokemonTypeChip(type: PokemonType) {
    TransparentCardWithElevation(
        modifier = Modifier
            .padding(4.dp)
            .padding(bottom = 8.dp)
    ){
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(type.hexColor.toColor()),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = type.name,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
                color = Color.White
            )
        }
    }
}