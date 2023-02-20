package com.yotfr.owes.domain.model

import java.time.LocalDate

data class Debt(
    val id: Long?,
    val amount: Long,
    val debtStatus: DebtStatus,
    val takingDate: LocalDate?,
    val repaymentDate: LocalDate?,
    val commentaryMessage: String?,
    val personId: Long
)
