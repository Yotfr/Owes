package com.yotfr.owes.data.util

import com.yotfr.owes.data.datasource.local.DebtEntity
import com.yotfr.owes.data.datasource.local.DebtWithPersonRelation
import com.yotfr.owes.data.datasource.local.PersonEntity
import com.yotfr.owes.data.datasource.local.PersonWithDebtsRelation
import com.yotfr.owes.domain.model.*


fun DebtEntity.mapToDebt() : Debt {
    return Debt(
        id = id,
        amount = amount,
        debtStatus = if (isDebtTaken) DebtStatus.TAKEN else DebtStatus.GIVEN,
        takingDate = takingDate,
        repaymentDate = repaymentDate,
        commentaryMessage = commentaryMessage,
        personId = personId
    )
}

fun Debt.mapToDebtEntity() : DebtEntity {
    return DebtEntity(
        id = id,
        amount = amount,
        isDebtTaken = debtStatus == DebtStatus.TAKEN,
        takingDate = takingDate,
        repaymentDate = repaymentDate,
        commentaryMessage = commentaryMessage,
        personId = personId
    )
}

fun PersonEntity.mapToPerson() : Person {
    return Person(
        id = id,
        name = name,
        phoneNumber = phoneNumber
    )
}

fun Person.mapToPersonEntity() : PersonEntity {
    return PersonEntity(
        id = id,
        name = name,
        phoneNumber = phoneNumber
    )
}

fun DebtWithPersonRelation.mapToDebtWithPerson() : DebtWithPerson {
    return DebtWithPerson(
        debt = debt.mapToDebt(),
        person = person.mapToPerson()
    )
}

fun PersonWithDebtsRelation.mapToPersonWithDebts() : PersonWithDebts {
    return PersonWithDebts(
        person = person.mapToPerson(),
        debts = debts.map { it.mapToDebt() }
    )
}
