package di

import org.koin.dsl.module
import ui.viewmodel.ViewModel

val viewModelModule = module {
    single<ViewModel> { ViewModel(get(), get(), get()) }
}