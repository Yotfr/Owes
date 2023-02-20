package com.yotfr.owes.app.screens.takendebts

import com.yotfr.owes.domain.model.DebtWithPerson

data class TakenDebtsState(
    val debts: List<DebtWithPerson> = emptyList()
)
