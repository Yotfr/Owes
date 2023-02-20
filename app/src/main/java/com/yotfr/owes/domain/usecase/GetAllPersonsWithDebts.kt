package com.yotfr.owes.domain.usecase

import com.yotfr.owes.domain.model.PersonWithDebts
import com.yotfr.owes.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow

class GetAllPersonsWithDebts(
    private val personRepository: PersonRepository
) {
    operator fun invoke(): Flow<List<PersonWithDebts>> {
        return personRepository.getAllPersonsWithDebts()
    }
}