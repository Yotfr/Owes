package com.yotfr.owes.app.screens.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yotfr.owes.domain.usecase.GetAllPersonsWithDebts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getAllPersonsWithDebts: GetAllPersonsWithDebts
) : ViewModel() {

    private val _state = MutableStateFlow(PeopleState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAllPersonsWithDebts().collectLatest { personWithDebts ->
                _state.update {
                    it.copy(
                        persons = UiPerson.fromDomainList(
                            persons = personWithDebts
                        )
                    )
                }
            }
        }
    }

}