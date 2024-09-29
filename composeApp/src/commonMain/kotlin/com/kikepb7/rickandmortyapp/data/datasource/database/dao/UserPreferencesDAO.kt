package com.kikepb7.rickandmortyapp.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kikepb7.rickandmortyapp.data.datasource.database.entity.CharacterEntity

@Dao
interface UserPreferencesDAO {
    @Query(value = "SELECT * FROM characteroftheday")
    suspend fun getCharacterOfTheDayDB(): CharacterEntity?

    @Insert(entity = CharacterEntity::class)
    suspend fun saveCharacter(characterEntity: CharacterEntity)
}