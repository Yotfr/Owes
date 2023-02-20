package com.yotfr.owes.app.screens.givendebts

import com.yotfr.owes.domain.model.DebtWithPerson

data class GivenDebtsState(
    val debts: List<DebtWithPerson> = emptyList()
)
