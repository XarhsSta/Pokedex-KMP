package ui.assets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ui.screens.HomeView

@Composable
fun PokedexTopAppBar(
    currentScreen: Screen,
    title: String
) {
    val navigator = LocalNavigator.currentOrThrow
    TopAppBar(modifier = Modifier.fillMaxWidth()) {
        if (currentScreen !is HomeView) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Button",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { navigator.pop() }
            )
        }
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color.White
        )
    }
}