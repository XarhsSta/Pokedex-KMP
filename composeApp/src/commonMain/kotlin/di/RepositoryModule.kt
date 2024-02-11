package di

import data.repository.DefaultPokemonRepository
import data.repository.PokemonRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PokemonRepository> { DefaultPokemonRepository(get()) }
}