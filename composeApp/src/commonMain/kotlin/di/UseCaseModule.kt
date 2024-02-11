package di

import domain.GetPokemonByIdUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetPokemonByIdUseCase> { GetPokemonByIdUseCase(get()) }
}