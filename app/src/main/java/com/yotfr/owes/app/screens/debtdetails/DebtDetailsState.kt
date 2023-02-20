package com.yotfr.owes.app.screens.debtdetails

import com.yotfr.owes.domain.model.DebtStatus
import java.time.LocalDate

data class DebtDetailsState(
    val debtAmount: String? = null,
    val debtCommentary: String = "",
    val debtStatus: DebtStatus = DebtStatus.TAKEN,
    val takingDate: LocalDate? = null,
    val takingDateString: String = "",
    val repaymentDate: LocalDate? = null,
    val repaymentDateString: String = "",
    val personName: String = "",
    val personPhoneNumber: String = ""
)