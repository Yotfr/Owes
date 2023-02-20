package com.yotfr.owes.data.repository

import com.yotfr.owes.data.datasource.local.PersonDao
import com.yotfr.owes.data.util.mapToPerson
import com.yotfr.owes.data.util.mapToPersonEntity
import com.yotfr.owes.domain.model.Person
import com.yotfr.owes.domain.repository.PersonRepository
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
}
