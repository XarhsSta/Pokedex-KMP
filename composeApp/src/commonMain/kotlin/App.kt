import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import di.networkModule
import di.repositoryModule
import di.useCaseModule
import di.viewModelModule
import org.koin.compose.KoinApplication
import ui.screens.HomeView

@Composable
fun App() {
    KoinApplication(application = {
        modules(
            listOf(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        )
    }) {
        MaterialTheme {
            Navigator(HomeView)
        }
    }
}