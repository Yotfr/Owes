package com.yotfr.owes.app.screens.people

import com.yotfr.owes.domain.model.DebtStatus
import com.yotfr.owes.domain.model.PersonWithDebts

data class UiPerson(
    val id: Long,
    val personName: String,
    val takenDebtAmount: String,
    val givenDebtAmount: String
) {
    companion object {
        fun fromDomain(person: PersonWithDebts): UiPerson {
            return UiPerson(
                id = person.person.id ?: throw IllegalArgumentException(
                    "If person exists personId cannot be null"
                ),
                personName = person.person.name,
                takenDebtAmount = person.debts
                    .filter { it.debtStatus == DebtStatus.TAKEN }
                    .sumOf { it.amount }.toString(),
                givenDebtAmount = person.debts
                    .filter { it.debtStatus == DebtStatus.GIVEN }
                    .sumOf { it.amount }.toString()
            )
        }
        fun fromDomainList(persons: List<PersonWithDebts>): List<UiPerson> {
            return persons.map { fromDomain(it) }
        }
    }
}
