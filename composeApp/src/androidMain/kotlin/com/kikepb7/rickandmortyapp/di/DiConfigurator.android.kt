package com.kikepb7.rickandmortyapp.di

import com.kikepb7.rickandmortyapp.data.database.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single {
            getDatabase(get())
        }
    }
}