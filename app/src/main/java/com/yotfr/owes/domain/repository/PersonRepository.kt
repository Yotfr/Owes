package com.yotfr.owes.domain.repository

import com.yotfr.owes.domain.model.Person
import com.yotfr.owes.domain.model.PersonWithDebts
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun createPerson(person: Person): Long
    suspend fun deletePerson(person: Person)
    suspend fun findPersonByName(name: String): Person?
    fun findPersonById(personId: Long): Flow<PersonWithDebts?>
    fun getAllPersonsWithDebts(): Flow<List<PersonWithDebts>>
}