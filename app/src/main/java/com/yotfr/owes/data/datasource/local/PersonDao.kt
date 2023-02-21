package com.yotfr.owes.data.datasource.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: PersonEntity): Long

    @Delete
    suspend fun deletePerson(person: PersonEntity)

    @Query("SELECT * FROM person WHERE name = :name")
    suspend fun findPersonByName(name: String): PersonEntity?

    @Transaction
    @Query("SELECT * FROM person WHERE id = :personId")
    fun findPersonById(personId: Long): Flow<PersonWithDebtsRelation?>

    @Transaction
    @Query("SELECT * FROM person")
    fun getAllPerson(): Flow<List<PersonWithDebtsRelation>>
}
