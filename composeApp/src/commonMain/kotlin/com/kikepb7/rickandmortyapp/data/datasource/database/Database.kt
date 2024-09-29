package com.kikepb7.rickandmortyapp.data.datasource.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.kikepb7.rickandmortyapp.data.datasource.database.dao.UserPreferencesDAO
import com.kikepb7.rickandmortyapp.data.datasource.database.entity.CharacterEntity

const val DATABASE_NAME = "rickmorty_app_database.db"

expect object RickMortyCTor: RoomDatabaseConstructor<RickMortyDatabase>

@Database(entities = [CharacterEntity::class], version = 1)
@ConstructedBy(value = RickMortyCTor::class)
abstract class RickMortyDatabase:RoomDatabase() {
    abstract fun getPreferencesDao(): UserPreferencesDAO
}