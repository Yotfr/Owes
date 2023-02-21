package com.yotfr.owes.domain.usecase

import com.yotfr.owes.domain.model.PersonWithDebts
import com.yotfr.owes.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow

class FindPersonByIdUseCase(
    private val personRepository: PersonRepository
) {
    operator fun invoke(personId: Long): Flow<PersonWithDebts?> {
        return personRepository.findPersonById(
            personId = personId
        )
    }
}