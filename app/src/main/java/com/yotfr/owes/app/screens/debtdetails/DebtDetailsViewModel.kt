package com.yotfr.owes.app.screens.debtdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yotfr.owes.domain.usecase.AddNewDebtUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class DebtDetailsViewModel @Inject constructor(
    private val addNewDebtUseCase: AddNewDebtUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DebtDetailsState())
    val state = _state.asStateFlow()

    private val _debtId = MutableStateFlow<Long?>(null)

    fun onEvent(event: DebtDetailsEvent) {
        when (event) {
            is DebtDetailsEvent.DebtAmountChanged -> {
                _state.update {
                    it.copy(
                        debtAmount = event.newAmount
                    )
                }
                // TODO: validate amount
            }
            is DebtDetailsEvent.DebtCommentaryChanged -> {
                _state.update {
                    it.copy(
                        debtCommentary = event.newCommentary
                    )
                }
            }
            is DebtDetailsEvent.DebtRepaymentDateChanged -> {
                val newRepaymentDateString = event.newDate.format(
                    DateTimeFormatter.ISO_DATE
                )
                _state.update {
                    it.copy(
                        repaymentDate = event.newDate,
                        repaymentDateString = newRepaymentDateString
                    )
                }
            }
            is DebtDetailsEvent.DebtStatusChanged -> {
                _state.update {
                    it.copy(
                        debtStatus = event.newStatus
                    )
                }
            }
            is DebtDetailsEvent.DebtTakingDateChanged -> {
                val newTakingDateString = event.newDate.format(
                    DateTimeFormatter.ISO_DATE
                )
                _state.update {
                    it.copy(
                        takingDate = event.newDate,
                        takingDateString = newTakingDateString
                    )
                }
            }
            is DebtDetailsEvent.PersonNameChanged -> {
                _state.update {
                    it.copy(
                        personName = event.newName
                    )
                }
            }
            is DebtDetailsEvent.PersonPhoneNumberChanged -> {
                _state.update {
                    it.copy(
                        personPhoneNumber = event.newNumber
                    )
                }
            }
            DebtDetailsEvent.SaveClicked -> {
                viewModelScope.launch {
                    if (_state.value.debtAmount != null && _state.value.personName.isNotBlank() ) {
                        addNewDebtUseCase(
                            debtId = _debtId.value,
                            debtAmount = _state.value.debtAmount!!.toLong(),
                            debtStatus = _state.value.debtStatus,
                            takingDate = _state.value.takingDate,
                            repaymentDate = _state.value.repaymentDate,
                            commentaryMessage = _state.value.debtCommentary,
                            personName = _state.value.personName,
                            phoneNumber = _state.value.personPhoneNumber
                        )
                    }
                }
            }
        }
    }
}
