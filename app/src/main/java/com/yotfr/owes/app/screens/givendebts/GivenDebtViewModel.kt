package com.yotfr.owes.app.screens.givendebts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yotfr.owes.domain.usecase.GetAllGivenDebtsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GivenDebtViewModel @Inject constructor(
    private val getAllGivenDebtsUseCase: GetAllGivenDebtsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(GivenDebtsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAllGivenDebtsUseCase().collectLatest { debtsList ->
                _state.update {
                    it.copy(
                        debts = debtsList
                    )
                }
            }
        }
    }
}