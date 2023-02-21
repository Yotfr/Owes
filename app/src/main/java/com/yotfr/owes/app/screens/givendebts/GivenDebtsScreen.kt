package com.yotfr.owes.app.screens.givendebts

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yotfr.owes.app.navigation.GlobalScreenRoutes
import com.yotfr.owes.app.screens.DebtItem
import java.time.format.DateTimeFormatter

@Composable
fun GivenDebtsScreen(
    navController: NavController,
    viewModel: GivenDebtViewModel = hiltViewModel()
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
                        GlobalScreenRoutes.DebtDetailsScreen.passDebtId(
                            debtId = item.debt.id ?: throw IllegalArgumentException(
                                "If item exists itemId cannot be null"
                            )
                        )
                    )
                },
                onPersonClicked = {
                    navController.navigate(
                        GlobalScreenRoutes.PersonDetailScreen.passPersonId(
                            personId = item.person.id ?: throw IllegalArgumentException(
                                "If item exists personId cannot be null"
                            )
                        )
                    )
                },
                repaymentDate = item.debt.repaymentDate?.format(
                    DateTimeFormatter.ISO_DATE
                ) ?: "Without time limit"
            )
        }
    }
}
