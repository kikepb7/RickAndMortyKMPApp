package com.kikepb7.rickandmortyapp.data.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.kikepb7.rickandmortyapp.data.datasource.database.DATABASE_NAME
import com.kikepb7.rickandmortyapp.data.datasource.database.RickMortyDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

fun getDatabase(): RickMortyDatabase {
    val dbFile = "${fileDirectory()}/$DATABASE_NAME"

    return Room.databaseBuilder<RickMortyDatabase>(name = dbFile)
        .setDriver(driver = BundledSQLiteDriver())
        .setQueryCoroutineContext(context = Dispatchers.IO)
        .build()
}

@OptIn(ExperimentalForeignApi::class)
fun fileDirectory(): String {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropiateForURL = null,
        create = false,
        error = null
    )
//    return requireNotNull(value = documentDirectory).path!!
    return requireNotNull(value = documentDirectory?.path)
}