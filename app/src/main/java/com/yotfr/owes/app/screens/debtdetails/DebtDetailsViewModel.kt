package com.yotfr.owes.app.screens.debtdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yotfr.owes.app.navigation.DEBT_ID_ARGUMENT_KEY
import com.yotfr.owes.app.navigation.WITHOUT_DEBT_ID
import com.yotfr.owes.domain.model.DebtWithPerson
import com.yotfr.owes.domain.usecase.AddNewDebtUseCase
import com.yotfr.owes.domain.usecase.FindDebtByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class DebtDetailsViewModel @Inject constructor(
    private val addNewDebtUseCase: AddNewDebtUseCase,
    private val findDebtByIdUseCase: FindDebtByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _debtId: Long = checkNotNull(savedStateHandle[DEBT_ID_ARGUMENT_KEY])

    private val _state = MutableStateFlow(DebtDetailsState())
    val state = _state.asStateFlow()

    init {
        if (_debtId != WITHOUT_DEBT_ID) {
            viewModelScope.launch {
                findDebtByIdUseCase(_debtId).collectLatest { debtWithPerson ->
                    processDebtWithPersonData(debtWithPerson)
                }
            }
        }
    }

    fun onEvent(event: DebtDetailsEvent) {
        when (event) {
            is DebtDetailsEvent.DebtAmountChanged -> {
                _state.update {
                    it.copy(
                        debtAmount = event.newAmount
                    )
                }
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
                    if (_state.value.debtAmount != null && _state.value.personName.isNotBlank()) {
                        addNewDebtUseCase(
                            debtId = if(_debtId == WITHOUT_DEBT_ID) null else _debtId,
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

    private fun processDebtWithPersonData(debtWithPerson: DebtWithPerson?) {
        debtWithPerson?.let {
            _state.update {
                it.copy(
                    debtAmount = debtWithPerson.debt.amount.toString(),
                    debtCommentary = debtWithPerson.debt.commentaryMessage ?: "",
                    debtStatus = debtWithPerson.debt.debtStatus,
                    takingDate = debtWithPerson.debt.takingDate,
                    takingDateString = debtWithPerson.debt.takingDate?.format(
                        DateTimeFormatter.ISO_DATE
                    ) ?: "taking date",
                    repaymentDate = debtWithPerson.debt.repaymentDate,
                    repaymentDateString = debtWithPerson.debt.repaymentDate?.format(
                        DateTimeFormatter.ISO_DATE
                    ) ?: "repayment date",
                    personName = debtWithPerson.person.name,
                    personPhoneNumber = debtWithPerson.person.phoneNumber ?: ""
                )
            }
        } ?: kotlin.run {
            throw IllegalArgumentException(
                "DebtWithPerson cannot be null if debtId not null"
            )
        }
    }
}
