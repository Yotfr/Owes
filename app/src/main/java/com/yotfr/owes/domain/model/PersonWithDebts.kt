package com.yotfr.owes.domain.model

data class PersonWithDebts(
    val person: Person,
    val debts: List<Debt>
)
