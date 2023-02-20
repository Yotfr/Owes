package com.yotfr.owes.app.screens.takendebts

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yotfr.owes.app.screens.DebtItem
@Composable
fun TakenDebtsScreen(
    navController: NavController,
    viewModel: TakenDebtsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    LazyColumn {
        items(state.value.debts) { item ->
            DebtItem(
                debtAmount = item.debt.amount.toString(),
                debtCommentary = item.debt.commentaryMessage ?: "",
                personName = item.person.name,
                onItemClicked = { },
                onPersonClicked = { }
            )
        }
    }
}