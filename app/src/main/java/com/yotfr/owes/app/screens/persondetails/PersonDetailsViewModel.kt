package com.yotfr.owes.app.screens.persondetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yotfr.owes.app.navigation.PERSON_ID_ARGUMENT_KEY
import com.yotfr.owes.app.screens.UiPerson
import com.yotfr.owes.domain.usecase.FindPersonByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val findPersonByIdUseCase: FindPersonByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _personId: Long = checkNotNull(savedStateHandle[PERSON_ID_ARGUMENT_KEY])

    private val _state = MutableStateFlow(PersonDetailsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            findPersonByIdUseCase(_personId).collectLatest { personWithDebts ->
                _state.update {
                    it.copy(
                        person = UiPerson.fromDomain(
                            personWithDebts ?: throw IllegalArgumentException(
                                "Person cannot be null if personId not null"
                            )
                        ),
                        debts = personWithDebts.debts
                    )
                }
            }
        }
    }
}
