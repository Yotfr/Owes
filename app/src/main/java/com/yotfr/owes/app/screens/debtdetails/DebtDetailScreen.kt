@file:OptIn(ExperimentalMaterial3Api::class)

package com.yotfr.owes.app.screens.debtdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.yotfr.owes.R
import com.yotfr.owes.app.utils.spacing
import com.yotfr.owes.domain.model.DebtStatus
import java.time.LocalDate

@Composable
fun DebtDetailsScreen(
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
                amountText = state.debtAmount ?: "",
                onAmountTextChanged = { newAmount ->
                    viewModel.onEvent(
                        DebtDetailsEvent.DebtAmountChanged(
                            newAmount = newAmount
                        )
                    )
                }
            )
            Divider()
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
            Divider()
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
            Divider()
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
            Divider()
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
        modifier = modifier
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(
            onClick = {
                onTakingDateClicked()
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.DateRange,
                contentDescription = "Date"
            )
            Text(text = takingDate)
        }
        TextButton(
            onClick = {
                onRepaymentDateClicked()
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.DateRange,
                contentDescription = "Date"
            )
            Text(text = repaymentDate)
        }
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
        Row(
            modifier = Modifier.fillMaxWidth()
                .clickable { expanded = true }
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.medium
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = currentDebtStatus.name,
                modifier = Modifier
            )
            Icon(
                imageVector = Icons.Outlined.ArrowDropDown,
                contentDescription = "Debt status"
            )
        }
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
        modifier = modifier,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_currency_rubble),
                contentDescription = "Currency"
            )
        },
        placeholder = {
            Text(text = "0")
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
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
            modifier = Modifier.fillMaxWidth(),
            value = personNameText,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Person"
                )
            },
            placeholder = {
                Text(text = "Person name")
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            ),
            onValueChange = {
                onPersonNameChanged(it)
            }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = personPhoneNumber,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone"
                )
            },
            placeholder = {
                Text(text = "Person phone number (optional)")
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            ),
            onValueChange = {
                onPersonPhoneNumberChanged(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
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
        modifier = modifier,
        placeholder = {
            Text(text = "Commentary")
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent
        )
    )
}
