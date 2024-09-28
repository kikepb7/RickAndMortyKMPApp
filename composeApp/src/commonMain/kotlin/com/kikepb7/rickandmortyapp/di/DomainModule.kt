package com.kikepb7.rickandmortyapp.di

import com.kikepb7.rickandmortyapp.domain.feature.characters.usecase.GetRandomCharacter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetRandomCharacter)
}