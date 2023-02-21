package com.yotfr.owes.data.repository

import com.yotfr.owes.data.datasource.local.PersonDao
import com.yotfr.owes.data.util.mapToPerson
import com.yotfr.owes.data.util.mapToPersonEntity
import com.yotfr.owes.data.util.mapToPersonWithDebts
import com.yotfr.owes.domain.model.Person
import com.yotfr.owes.domain.model.PersonWithDebts
import com.yotfr.owes.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val personDao: PersonDao
) : PersonRepository {
    override suspend fun createPerson(person: Person): Long {
        return personDao.insertPerson(
            person = person.mapToPersonEntity()
        )
    }

    override suspend fun deletePerson(person: Person) {
        personDao.deletePerson(
            person = person.mapToPersonEntity()
        )
    }

    override suspend fun findPersonByName(name: String): Person? {
        return personDao.findPersonByName(
            name = name
        )?.mapToPerson()
    }

    override fun findPersonById(personId: Long): Flow<PersonWithDebts?> {
        return personDao.findPersonById(
            personId = personId
        ).map { it?.mapToPersonWithDebts() }
    }

    override fun getAllPersonsWithDebts(): Flow<List<PersonWithDebts>> {
        return personDao.getAllPerson().map {
            it.map { it.mapToPersonWithDebts() }
        }
    }
}
