package com.souschef.di

import com.souschef.usecases.recipe.CreateRecipeUseCase
import com.souschef.usecases.recipe.PublishRecipeUseCase
import org.koin.dsl.module

/**
 * Use-case module — register use-case classes as `single` (stateless).
 */
val useCaseModule = module {
    single { CreateRecipeUseCase(get()) }
    single { PublishRecipeUseCase(get()) }
}
