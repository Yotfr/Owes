package com.yotfr.owes.app.screens.takendebts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yotfr.owes.domain.usecase.GetAllTakenDebtsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TakenDebtsViewModel @Inject constructor(
    private val getAllTakenDebtsUseCase: GetAllTakenDebtsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(TakenDebtsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAllTakenDebtsUseCase().collectLatest { debtsList ->
                _state.update {
                    it.copy(
                        debts = debtsList
                    )
                }
            }
        }
    }
}
