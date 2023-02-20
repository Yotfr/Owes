package com.yotfr.owes.app.screens.debtdetails

import com.yotfr.owes.domain.model.DebtStatus
import java.time.LocalDate

sealed interface DebtDetailsEvent {
    data class DebtAmountChanged(val newAmount: String) : DebtDetailsEvent
    data class DebtStatusChanged(val newStatus: DebtStatus) : DebtDetailsEvent
    data class DebtCommentaryChanged(val newCommentary: String) : DebtDetailsEvent
    data class DebtTakingDateChanged(val newDate: LocalDate) : DebtDetailsEvent
    data class DebtRepaymentDateChanged(val newDate: LocalDate) : DebtDetailsEvent
    data class PersonNameChanged(val newName: String) : DebtDetailsEvent
    data class PersonPhoneNumberChanged(val newNumber: String) : DebtDetailsEvent
    object SaveClicked : DebtDetailsEvent
}