package com.yotfr.owes.app.screens

import com.yotfr.owes.domain.model.DebtStatus
import com.yotfr.owes.domain.model.PersonWithDebts

data class UiPerson(
    val id: Long,
    val personName: String,
    val takenDebtAmount: String,
    val givenDebtAmount: String,
    val totalAmount: String
) {
    companion object {
        fun fromDomain(person: PersonWithDebts): UiPerson {
            val takenAmount = person.debts
                .filter { it.debtStatus == DebtStatus.TAKEN }
                .sumOf { it.amount }
            val givenAmount = person.debts
                .filter { it.debtStatus == DebtStatus.GIVEN }
                .sumOf { it.amount }
            return UiPerson(
                id = person.person.id ?: throw IllegalArgumentException(
                    "If person exists personId cannot be null"
                ),
                personName = person.person.name,
                takenDebtAmount = takenAmount.toString(),
                givenDebtAmount = givenAmount.toString(),
                totalAmount = (givenAmount - takenAmount).toString()
            )
        }
        fun fromDomainList(persons: List<PersonWithDebts>): List<UiPerson> {
            return persons.map { fromDomain(it) }
        }
    }
}
