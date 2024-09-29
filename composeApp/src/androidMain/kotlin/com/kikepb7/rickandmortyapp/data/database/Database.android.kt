package com.kikepb7.rickandmortyapp.data.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.kikepb7.rickandmortyapp.data.datasource.database.DATABASE_NAME
import com.kikepb7.rickandmortyapp.data.datasource.database.RickMortyDatabase
import kotlinx.coroutines.Dispatchers

fun getDatabase(context: Context): RickMortyDatabase {
    val dbFile = context.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<RickMortyDatabase>(context = context, name = dbFile.absolutePath)
        .setDriver(driver = BundledSQLiteDriver())
        .setQueryCoroutineContext(context = Dispatchers.IO)
        .build()
}