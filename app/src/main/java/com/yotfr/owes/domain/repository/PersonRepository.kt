package com.yotfr.owes.domain.repository

import com.yotfr.owes.domain.model.Person

interface PersonRepository {
    suspend fun createPerson(person: Person): Long
    suspend fun deletePerson(person: Person)
    suspend fun findPersonByName(name: String): Person?
}