package com.yotfr.owes.app.screens.persondetails

import com.yotfr.owes.app.screens.UiPerson
import com.yotfr.owes.domain.model.Debt

data class PersonDetailsState(
    val person: UiPerson? = null,
    val debts: List<Debt> = emptyList()
)