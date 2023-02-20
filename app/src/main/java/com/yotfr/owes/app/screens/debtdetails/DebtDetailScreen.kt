@file:OptIn(ExperimentalMaterial3Api::class)

package com.yotfr.owes.app.screens.debtdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.yotfr.owes.domain.model.DebtStatus
import java.time.LocalDate

@Composable
fun DebtDetailsSceen(
    navController: NavController,
    viewModel: DebtDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val takingDateDialogState = rememberMaterialDialogState()
    val repaymentDateDialogState = rememberMaterialDialogState()

    Scaffold(
        topBar = {
            DebtDetailsScreenTopBar(
                onSaveDebtClicked = {
                    viewModel.onEvent(
                        DebtDetailsEvent.SaveClicked
                    )
                },
                onClosePressed = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            AmountTextField(
                modifier = Modifier.fillMaxWidth(),
                amountText = state.debtAmount.toString(),
                onAmountTextChanged = { newAmount ->
                    viewModel.onEvent(
                        DebtDetailsEvent.DebtAmountChanged(
                            newAmount = newAmount
                        )
                    )
                }
            )
            DebtStatus(
                modifier = Modifier.fillMaxWidth(),
                currentDebtStatus = state.debtStatus,
                onDebtStatusChanged = { newStatus ->
                    viewModel.onEvent(
                        DebtDetailsEvent.DebtStatusChanged(
                            newStatus = newStatus
                        )
                    )
                }
            )
            PersonTextField(
                modifier = Modifier.fillMaxWidth(),
                personNameText = state.personName,
                personPhoneNumber = state.personPhoneNumber,
                onPersonNameChanged = { newName ->
                    viewModel.onEvent(
                        DebtDetailsEvent.PersonNameChanged(
                            newName = newName
                        )
                    )
                },
                onPersonPhoneNumberChanged = { newNumber ->
                    viewModel.onEvent(
                        DebtDetailsEvent.PersonPhoneNumberChanged(
                            newNumber = newNumber
                        )
                    )
                }
            )
            DebtDateFields(
                modifier = Modifier.fillMaxWidth(),
                takingDate = state.takingDateString,
                onTakingDateClicked = {
                    takingDateDialogState.show()
                },
                repaymentDate = state.repaymentDateString,
                onRepaymentDateClicked = {
                    repaymentDateDialogState.show()
                }
            )
            CommentaryTextField(
                modifier = Modifier.fillMaxWidth(),
                commentaryText = state.debtCommentary,
                onCommentaryChanged = { newCommentary ->
                    viewModel.onEvent(
                        DebtDetailsEvent.DebtCommentaryChanged(
                            newCommentary = newCommentary
                        )
                    )
                }
            )
        }
    }

    // TakingDialog
    MaterialDialog(
        dialogState = takingDateDialogState,
        buttons = {
            positiveButton(text = "Ok")
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick taking date",
            allowedDateValidator = {
                it >= LocalDate.now()
            }
        ) { newDate ->
            viewModel.onEvent(
                DebtDetailsEvent.DebtTakingDateChanged(
                    newDate = newDate
                )
            )
        }
    }

    // RepaymentDialog
    MaterialDialog(
        dialogState = repaymentDateDialogState,
        buttons = {
            positiveButton(text = "Ok")
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick repayment date",
            allowedDateValidator = {
                it >= LocalDate.now()
            }
        ) { newDate ->
            viewModel.onEvent(
                DebtDetailsEvent.DebtRepaymentDateChanged(
                    newDate = newDate
                )
            )
        }
    }
}

@Composable
fun DebtDetailsScreenTopBar(
    onSaveDebtClicked: () -> Unit,
    onClosePressed: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "New debt")
        },
        navigationIcon = {
            IconButton(onClick = { onClosePressed() }) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "Close"
                )
            }
        },
        actions = {
            IconButton(onClick = { onSaveDebtClicked() }) {
                Icon(
                    imageVector = Icons.Outlined.Done,
                    contentDescription = "Save"
                )
            }
        }
    )
}

@Composable
fun DebtDateFields(
    modifier: Modifier,
    takingDate: String,
    onTakingDateClicked: () -> Unit,
    repaymentDate: String,
    onRepaymentDateClicked: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = takingDate,
            modifier = modifier
                .clickable {
                    onTakingDateClicked()
                }
        )
        Text(
            text = repaymentDate,
            modifier = modifier
                .clickable {
                    onRepaymentDateClicked()
                }
        )
    }
}

@Composable
fun DebtStatus(
    modifier: Modifier,
    currentDebtStatus: DebtStatus,
    onDebtStatusChanged: (debtStatus: DebtStatus) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Text(
            text = currentDebtStatus.name,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = "Given") },
                onClick = { onDebtStatusChanged(DebtStatus.GIVEN) }
            )
            DropdownMenuItem(
                text = { Text(text = "Taken") },
                onClick = { onDebtStatusChanged(DebtStatus.TAKEN) }
            )
        }
    }
}

@Composable
fun AmountTextField(
    modifier: Modifier,
    amountText: String,
    onAmountTextChanged: (text: String) -> Unit
) {
    TextField(
        value = amountText,
        onValueChange = {
            onAmountTextChanged(it)
        },
        modifier = modifier
    )
}

@Composable
fun PersonTextField(
    modifier: Modifier,
    personNameText: String,
    personPhoneNumber: String,
    onPersonNameChanged: (text: String) -> Unit,
    onPersonPhoneNumberChanged: (text: String) -> Unit
) {
    Column(modifier = modifier) {
        TextField(
            value = personNameText,
            onValueChange = {
                onPersonNameChanged(it)
            }
        )
        TextField(
            value = personPhoneNumber,
            onValueChange = {
                onPersonPhoneNumberChanged(it)
            }
        )
    }
}

@Composable
fun CommentaryTextField(
    modifier: Modifier,
    commentaryText: String,
    onCommentaryChanged: (text: String) -> Unit
) {
    TextField(
        value = commentaryText,
        onValueChange = {
            onCommentaryChanged(it)
        },
        modifier = modifier
    )
}
