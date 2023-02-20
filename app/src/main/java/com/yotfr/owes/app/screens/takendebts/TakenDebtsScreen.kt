package com.yotfr.owes.app.screens.takendebts

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yotfr.owes.app.navigation.TopLevelScreenRoutes
import com.yotfr.owes.app.screens.DebtItem
import java.time.format.DateTimeFormatter

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
                onItemClicked = {
                    navController.navigate(
                        TopLevelScreenRoutes.DebtDetailsScreen.passDebtId(
                            debtId = item.debt.id ?: throw IllegalArgumentException(
                                "If item exists itemId cannot be null"
                            )
                        )
                    )
                },
                onPersonClicked = { },
                repaymentDate = item.debt.repaymentDate?.format(
                    DateTimeFormatter.ISO_DATE
                ) ?: "Without time limit"
            )
        }
    }
}
