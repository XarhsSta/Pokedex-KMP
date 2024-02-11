import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import ui.screens.HomeView

@Composable
fun App() {
    MaterialTheme {
        Navigator(HomeView)
    }
}